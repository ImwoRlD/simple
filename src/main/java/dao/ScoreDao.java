package dao;

import Model.Student_Score_VO;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreDao {
    void saveStudentScore(Student_Score_VO vo);
}
