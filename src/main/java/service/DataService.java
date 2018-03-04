package service;

import Model.StudentInfo;
import com.alibaba.fastjson.JSONObject;
import dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    @Autowired
    private StudentDao studentDao;
    public String personData(String username){
        StudentInfo studentInfo=studentDao.getStudentByUsername(username);
        JSONObject json=new JSONObject();
        json.put("info",studentInfo);
    }
}
