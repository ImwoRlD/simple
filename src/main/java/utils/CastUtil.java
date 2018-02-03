package utils;

import Model.Score;
import Model.Term;
import Model.TimeScore;

import java.util.ArrayList;
import java.util.Map;

public class CastUtil {
    public static final String URL="http://202.194.48.15/";
    public static void addScoreToScoreMap(Map<Term, TimeScore> map, Term term, Score score){
        if (map.get(term)==null){
            TimeScore timeScore=new TimeScore();
            timeScore.setTerm(term);
            timeScore.setScoreList(new ArrayList<>());
            timeScore.getScoreList().add(score);
            map.put(term,timeScore);
        }else {
            map.get(term).getScoreList().add(score);
        }
    }
    public static String buildUrl(String str){
        String url;
        switch (str){
            case "captcha":
                url=URL+"validateCodeAction.do";
                break;
            case "login":
                url=URL+"loginAction.do";
                break;
            case "info":
                url=URL+"xjInfoAction.do?oper=xjxx";
                break;
            case "pass":
                url=URL+"/gradeLnAllAction.do?type=ln&oper=qbinfo&lnxndm=2017-2018%D1%A7%C4%EA%C7%EF(%C1%BD%D1%A7%C6%DA)";
                break;
            case "fail":
                url=URL+"/gradeLnAllAction.do?type=ln&oper=bjg";
                break;
            default:
                url=URL+"loginAction.do";
                break;
        }
        return url;
    }
}
