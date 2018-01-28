package Model;

import java.util.List;

public class StudentScore {
    public String name;
    public Float totalPoint;
    public Float GPA;
    public List<TimeScore> timeScoreList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Float totalPoint) {
        this.totalPoint = totalPoint;
    }

    public Float getGPA() {
        return GPA;
    }

    public void setGPA(Float GPA) {
        this.GPA = GPA;
    }

    public List<TimeScore> getTimeScoreList() {
        return timeScoreList;
    }

    public void setTimeScoreList(List<TimeScore> timeScoreList) {
        this.timeScoreList = timeScoreList;
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "name='" + name + '\'' +
                ", totalPoint=" + totalPoint +
                ", GPA=" + GPA +
                ", timeScoreList=" + timeScoreList +
                '}';
    }
}
