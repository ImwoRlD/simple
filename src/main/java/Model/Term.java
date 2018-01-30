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
    public int hashCode() {
        return termNum*31-2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj){
            return true;
        }
        if (obj==null||getClass()!=obj.getClass()){
            return false;
        }
        Term term=(Term)obj;
        if (!term.text.equals(this.text)){
            return false;
        }
        if (term.termNum!=this.termNum){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Term{" +
                "text='" + text + '\'' +
                ", termNum=" + termNum +
                '}';
    }
}
