package dao;

import Model.Student_Score;
import Model.Student_Score_VO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScoreDao {
    void saveStudentScore(Student_Score_VO vo);
    List<Student_Score> getScoreByPage(String username,Integer n);
    int getCountByUsername(String name);
    List<Map> getPointMap(String name);
}
