package utils;

import Model.Term;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.HashMap;
import java.util.Map;

public class ParseUtil {
    public static final String SPRING_TERM="学年春(两学期)";
    public static final String AUTUMN_TERM="学年秋(两学期)";
    public static final Map<String,Integer> SCORE_MAP;
    static {
        SCORE_MAP=new HashMap<String,Integer>(16);
        SCORE_MAP.put("优秀",90);
        SCORE_MAP.put("良好",80);
        SCORE_MAP.put("中等",70);
        SCORE_MAP.put("及格",60);
        SCORE_MAP.put("不及格",0);
    }
    public static int parseTermNo(String str){
        if (str.contains("春")){
            return 2;
        }
        if (str.contains("秋")){
            return 1;
        }
        return 0;
    }
    public static int parseNumScore(String str){
        if (SCORE_MAP.get(str)!=null){
            return SCORE_MAP.get(str);
        }
        else {
            str=str.split(".")[0];
            return Integer.parseInt(str);
        }
    }
    public static Term str2Term(String str){
        Term term=new Term();
        String temp=str.substring(5,6);
        String termText=str.substring(0,4);
        if (Integer.parseInt(temp)>=6){
            term.setText((Integer.parseInt(termText)-1)+"-"+temp+SPRING_TERM);
            term.setTermNum(2);
        }else {
            term.setText(temp+"-"+(Integer.parseInt(termText)+1)+AUTUMN_TERM);
            term.setTermNum(1);
        }
        return term;
    }
}
