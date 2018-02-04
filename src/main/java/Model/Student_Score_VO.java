package Model;

public class Student_Score_VO {
    private StudentInfo studentInfo;
    private Score score;
    private Term term;

    public Student_Score_VO(StudentInfo studentInfo, Score score, Term term) {
        this.studentInfo = studentInfo;
        this.score = score;
        this.term = term;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
