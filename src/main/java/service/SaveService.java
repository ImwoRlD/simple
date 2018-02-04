package service;

import Model.*;
import dao.ScoreDao;
import dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ScoreDao scoreDao;
    public void saveStudent(StudentInfo studentInfo){
        studentDao.saveStudent(studentInfo);
    }
    public void saveStudentScore(StudentInfo studentInfo, List<TimeScore> list){
        for (int i=0;i<list.size();i++){
            TimeScore timeScore=list.get(i);
            List<Score> scoreList=timeScore.getScoreList();
            Term term=timeScore.getTerm();
            for (int j=0;j<scoreList.size();j++){
                Score score=scoreList.get(j);
                Student_Score_VO vo=new Student_Score_VO(studentInfo,score,term);
                scoreDao.saveStudentScore(vo);
            }
        }
    }
}
