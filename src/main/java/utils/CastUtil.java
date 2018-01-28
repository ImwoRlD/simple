package utils;

import Model.Score;
import Model.Term;
import Model.TimeScore;

import java.util.ArrayList;
import java.util.Map;

public class CastUtil {
    public static void addScoreToScoreMap(Map<Term, TimeScore> map, Term term, Score score){
        if (map.get(term)==null){
            TimeScore timeScore=new TimeScore();
            timeScore.setTerm(term);
            timeScore.setScoreList(new ArrayList<>());
            timeScore.getScoreList().add(score);
        }
        map.get(term).getScoreList().add(score);
    }
}
