package Model;

import java.util.List;

public class TimeScore {
    public Term term;

    public Float GPA;
    public List<Score> scoreList;

    public Float getGPA() {
        return GPA;
    }

    public void setGPA(Float GPA) {
        this.GPA = GPA;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "TimeScore{" +
                "term=" + term +
                ", GPA=" + GPA +
                ", scoreList=" + scoreList +
                '}';
    }
}
