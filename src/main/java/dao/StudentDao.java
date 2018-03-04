package dao;

import Model.StudentInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {
    void saveStudent(StudentInfo studentInfo);
    StudentInfo getStudentByUsername(String username);
}
