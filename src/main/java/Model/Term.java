package Model;

public class Term {
    private String text;
    private Integer termNum;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTermNum() {
        return termNum;
    }

    public void setTermNum(Integer termNum) {
        this.termNum = termNum;
    }

    @Override
    public String toString() {
        return "Term{" +
                "text='" + text + '\'' +
                ", termNum=" + termNum +
                '}';
    }
}
