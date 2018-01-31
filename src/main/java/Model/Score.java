package Model;

import java.io.Serializable;

public class Score implements Serializable{
    private String name;
    private Integer point;
    private String type;
    private String score;
    private Integer number;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Score{" +
                "name='" + name + '\'' +
                ", point=" + point +
                ", type='" + type + '\'' +
                ", score='" + score + '\'' +
                ", number=" + number +
                ", remark='" + remark + '\'' +
                '}';
    }
}
