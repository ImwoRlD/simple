package utils;

import java.util.HashMap;
import java.util.Map;

public class ParseUtil {
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
}
