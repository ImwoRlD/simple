package dao;

import Model.StudentInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {
    public void saveStudent(StudentInfo studentInfo);
}
