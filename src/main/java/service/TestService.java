package service;

import Model.StudentInfo;
import Model.TimeScore;
import crawler.HtmlParser.HtmlParser;
import crawler.HttpRequest.HttpRequest;
import crawler.HttpRequest.HttpResponse;
import crawler.HttpRequest.RequestContext;
import org.springframework.stereotype.Service;
import utils.CastUtil;

import java.util.List;

@Service
public class TestService {
    public HtmlParser parser=new HtmlParser();
    public String getcaptcha(String username,String password){
        RequestContext requestContext=new RequestContext();
        requestContext.setSessioonId(username);
        requestContext.setClear(true);
        requestContext.GET().url(CastUtil.buildUrl("captcha")).setUpload(true);
        HttpRequest request=new HttpRequest(requestContext);
        HttpResponse response=request.sendWithCookieStore();
        return response.getImageAddres();
    }
    public String login(String username,String password,String captcha){
        RequestContext loginContext=new RequestContext();
        loginContext.setSessioonId(username);
        loginContext.setResponseCharset("gb2312");
        loginContext.POST().url(CastUtil.buildUrl("login"))
                .form("zjh",username)
                .form("mm",password)
                .form("v_yzm",captcha);
        HttpResponse loginresult=new HttpRequest(loginContext).sendWithCookieStore();
        String result=loginresult.string();
        RequestContext infoContext=new RequestContext();
        infoContext.setSessioonId(username);
        infoContext.setResponseCharset("gb2312");
        infoContext.GET().url(CastUtil.buildUrl("info"));
        HttpResponse inforesult=new HttpRequest(infoContext).sendWithCookieStore();
        String info=inforesult.string();
        StudentInfo studentInfo=parser.parseStudentInfo(info);
        RequestContext passContext=new RequestContext();
        passContext.setSessioonId(username);
        passContext.setResponseCharset("gb2312");
        passContext.GET().url(CastUtil.buildUrl("pass"));
        HttpResponse passresult=new HttpRequest(passContext).sendWithCookieStore();
        String passhtml=passresult.string();
        RequestContext failContext=new RequestContext();
        failContext.setSessioonId(username);
        failContext.setResponseCharset("gb2312");
        failContext.GET().url(CastUtil.buildUrl("fail"));
        HttpResponse failresult=new HttpRequest(failContext).sendWithCookieStore();
        String failhtml=failresult.string();
        List<TimeScore> list=parser.parseStudentScore(passhtml,failhtml);
        return "sdsa";
    }
}
