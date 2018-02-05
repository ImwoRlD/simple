package service;

import Model.StudentInfo;
import Model.TimeScore;
import crawler.HtmlParser.HtmlParser;
import crawler.HttpRequest.HttpRequest;
import crawler.HttpRequest.HttpResponse;
import crawler.HttpRequest.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.CastUtil;

import java.util.List;

@Service
public class FetchService {
    @Autowired
    private SaveService saveService;
    private HtmlParser parser=new HtmlParser();
    public String getCaptcha(String sessionId){
        RequestContext context=new RequestContext();
        context.setSessioonId(sessionId);
        context.url(CastUtil.buildUrl("captcha")).GET().setUpload(true);
        HttpRequest request=new HttpRequest(context);
        HttpResponse response=request.sendWithCookieStore();
        return response.getImageAddres();
    }
    public String loginAndFetch(String username,String password,String captcha,String sessionId){
        RequestContext context=new RequestContext();
        context.setSessioonId(sessionId);
        context.setResponseCharset("gb2312");
        context.url(CastUtil.buildUrl("login")).POST()
                .form("zjh",username)
                .form("mm",password)
                .form("v_yzm",captcha);
        HttpRequest request=new HttpRequest(context);
        HttpResponse response=request.sendWithCookieStore();
        if (!response.string().contains("学分XXXX")){
            return "FAIL";
        }
        RequestContext infoContext=new RequestContext();
        context.setSessioonId(sessionId);
        context.setResponseCharset("gb2312");
        infoContext.GET().url(CastUtil.buildUrl("info"));
        HttpResponse inforesult=new HttpRequest(infoContext).sendWithCookieStore();
        StudentInfo studentInfo=parser.parseStudentInfo(inforesult.string());
        saveService.saveStudent(studentInfo);
        RequestContext passContext=new RequestContext();
        passContext.setSessioonId(sessionId);
        passContext.setResponseCharset("gb2312");
        passContext.GET().url(CastUtil.buildUrl("pass"));
        HttpResponse passresult=new HttpRequest(passContext).sendWithCookieStore();
        String passhtml=passresult.string();
        RequestContext failContext=new RequestContext();
        failContext.setSessioonId(sessionId);
        failContext.setResponseCharset("gb2312");
        failContext.GET().url(CastUtil.buildUrl("fail"));
        HttpResponse failresult=new HttpRequest(failContext).sendWithCookieStore();
        String failhtml=failresult.string();
        List<TimeScore> list=parser.parseStudentScore(passhtml,failhtml);
        saveService.saveStudentScore(studentInfo,list);
        return "SUCCESS";
    }
}
