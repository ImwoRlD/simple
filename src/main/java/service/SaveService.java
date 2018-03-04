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
    }
}
