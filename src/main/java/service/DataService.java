package service;

import Model.StudentInfo;
import Model.Student_Score;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.ScoreDao;
import dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.JSONUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ScoreDao scoreDao;
    public String personData(String username,int n){
        JSONObject json=new JSONObject();
        //构建返回 info
        StudentInfo studentInfo=studentDao.getStudentByUsername(username);
        JSONObject info=JSONUtils.parseJSONObject(studentInfo);
        json.put("info",studentInfo);
        //构建选课，学分图
        JSONObject data=new JSONObject();
        JSONArray labels=new JSONArray();
        JSONArray datasets=new JSONArray();
        List<Map> maps=scoreDao.getPointMap(studentInfo.getName());
        for (Map m:maps){
            String label=m.get("term_text")+"-"+m.get("term_no");
            labels.add(label);
        }
        //返回一个总页数用于生成li
        int num=scoreDao.getCountByUsername(studentInfo.getName());
        int count=(int)(Math.ceil(num/10.0));
        json.put("countPage",count);
        //构建返回scores
        List<Student_Score> list=scoreDao.getScoreByPage(username,(n-1)*10);
        JSONArray scores=new JSONArray();
        for (Student_Score sco:list){
            JSONObject score= JSONUtils.parseJSONObject(sco);
            scores.add(score);
        }
        json.put("scores",scores);
        return json.toJSONString();
    }

    public String pageScore(String username, int n) {
        JSONObject json=new JSONObject();
        List<Student_Score> list=scoreDao.getScoreByPage(username,(n-1)*10);
        JSONArray scores=new JSONArray();
        for (Student_Score sco:list){
            JSONObject score= JSONUtils.parseJSONObject(sco);
            scores.add(score);
        }
        json.put("scores",scores);
        return json.toJSONString();
    }
}
