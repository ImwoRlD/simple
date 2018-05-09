package dao;

import Model.StudentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentDao {
    void saveStudent(StudentInfo studentInfo);
    StudentInfo getStudentByUsername(String username);
    List<Map> getAllAvg(String classId);
    List<Map> getProvinces(String classId);
    List<String> getClazz();
    List<Map> getNumber(String clazz,int n);
    List<Map> getAge(String clazz);
}
