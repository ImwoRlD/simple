package service;

import Model.*;
import crawler.HtmlParser.HtmlParser;
import crawler.HttpRequest.HttpRequest;
import crawler.HttpRequest.HttpResponse;
import crawler.HttpRequest.RequestContext;
import dao.ScoreDao;
import dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.CastUtil;
import utils.ContextFactory;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ScoreDao scoreDao;
    public HtmlParser parser=new HtmlParser();
    public String getcaptcha(String sessionid){
        RequestContext context=ContextFactory.getContext("getcaptcha",sessionid);
        HttpResponse response=new HttpRequest(context).sendWithCookieStore();
        return response.getImageAddres();
    }
    public void fetchData(String sessionid,String username,String password,String captcha){
        //登录获取cookie
        RequestContext loginContext= ContextFactory.getContext("login",sessionid,username,password,captcha);
        HttpResponse loginresult=new HttpRequest(loginContext).sendWithCookieStore();
        //爬取info 存储结果
        RequestContext infoContext=ContextFactory.getContext("info",sessionid);
        HttpResponse inforesult=new HttpRequest(infoContext).sendWithCookieStore();
        String info=inforesult.string();
        StudentInfo studentInfo=parser.parseStudentInfo(info);
        studentDao.saveStudent(studentInfo);
        //爬取pass成绩与fail成绩
        RequestContext passContext=ContextFactory.getContext("pass",sessionid);
        HttpResponse passresult=new HttpRequest(passContext).sendWithCookieStore();
        String passhtml=passresult.string();
        RequestContext failContext=ContextFactory.getContext("fail",sessionid);
        HttpResponse failresult=new HttpRequest(failContext).sendWithCookieStore();
        String failhtml=failresult.string();
        //存储成绩结果
        List<TimeScore> list=parser.parseStudentScore(passhtml,failhtml);
        saveStudentScore(studentInfo,list);
    }

    public boolean checkStudentInfo(String username) {
        StudentInfo studentInfo=studentDao.getStudentByUsername(username);
        if (studentInfo==null){
            return false;
        }
        return true;
    }
    private void saveStudentScore(StudentInfo studentInfo, List<TimeScore> list){
        for (int i=0;i<list.size();i++){
            TimeScore timeScore=list.get(i);
            List<Score> scoreList=timeScore.getScoreList();
            Term term=timeScore.getTerm();
            for (int j=0;j<scoreList.size();j++){
                Score score=scoreList.get(j);
                Student_Score_VO vo=new Student_Score_VO(studentInfo,score,term);
                scoreDao.saveStudentScore(vo);
            }
        }
    }
}
