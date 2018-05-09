package service;

import Model.StudentInfo;
import Model.Student_Score;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.ScoreDao;
import dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ChartJson;
import utils.JSONUtils;

import java.math.BigDecimal;
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
        String name=studentInfo.getName();
        //构建选课，学分图
        json.put("point",getPoint(name));
        //构建平均成绩 绩点图
        json.put("score",getScore(name));
        //构建选课分布柱状图
        json.put("type",getType(name));
        //构建 pie图
        json.put("pie",getPie(name));
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

    private JSONObject getType(String name) {
        List<Map> map=scoreDao.getType(name);
        ChartJson chart=new ChartJson();
        chart.addLabel("任选").addLabel("必修").addLabel("选修");
        chart.addDatasetLabel("选课数(x10)").addDatasetLabel("总学分");
        for (Map m:map){
            Long xk=(Long) m.get("门数");
            BigDecimal xf=(BigDecimal) m.get("总学分");
            chart.addDatasetIntData("选课数(x10)",xk.intValue()*10);
            chart.addDatasetIntData("总学分",xf.intValue());
        }
        chart.addDatasetBackgroudColor("选课数(x10)","blue");
        chart.addDatasetBackgroudColor("总学分","yellow");
        chart.addDatasetBackgroudColor("选课数(x10)","blue");
        chart.addDatasetBackgroudColor("总学分","yellow");
        chart.addDatasetBackgroudColor("选课数(x10)","blue");
        chart.addDatasetBackgroudColor("总学分","yellow");
        return chart.build();
    }

    private JSONObject getPie(String name) {
        Map<String,Long> map=scoreDao.getScoreDis(name);
        ChartJson chart=new ChartJson();
        for(String key:map.keySet()){
            chart.addLabel(key);
            chart.addDatasetData(map.get(key));
        }
        return chart.addDatasetBackgroudColor().build();
    }

    private JSONObject getScore(String name) {
        ChartJson chart=new ChartJson();
        chart.addDatasetLabel("学期平均分");
        chart.addDatasetLabel("学期平均绩点(x10)");
        chart.addDatasetLabel("总平均绩点(x10)");
        List<Map> maps=scoreDao.getScoreMap(name);
        float sum=0;
        int i=1;
        for (Map m:maps){
            String label=m.get("term_text")+"-"+m.get("term_no");
            chart.addLabel(label);
            float avg=((BigDecimal)m.get("平均成绩")).floatValue();
            float sco=avg-50;
            sum+=sco;
            float avgpoint=sum/i;
            i++;
            chart.addDatasetFloatData("学期平均分",avg);
            chart.addDatasetFloatData("学期平均绩点(x10)",sco);
            chart.addDatasetFloatData("总平均绩点(x10)",avgpoint);
        }
        chart.addDatasetBackgroudColor("学期平均分","yellow");
        chart.addDatasetBackgroudColor("学期平均绩点(x10)","purple");
        chart.addDatasetBackgroudColor("总平均绩点(x10)","green");
        return chart.build();
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
    private JSONObject getPoint(String name){
        ChartJson chart=new ChartJson();
        chart.addDatasetLabel("学期选课数");
        chart.addDatasetLabel("学期学分数");
        chart.addDatasetLabel("总学分");
        int counttemp=0;
        List<Map> maps=scoreDao.getPointMap(name);
        for (Map m:maps){
            String label=m.get("term_text")+"-"+m.get("term_no");
            chart.addLabel(label);
            int temp1=Integer.parseInt(String.valueOf(m.get("选课数")));
            int temp2=((BigDecimal)m.get("总学分")).intValue();
            counttemp+=temp2;
            float sum=counttemp/100;
            chart.addDatasetIntData("学期选课数",temp1);
            chart.addDatasetFloatData("总学分",sum);
            chart.addDatasetIntData("学期学分数",temp2/100);
        }
        chart.addDatasetBackgroudColor("学期选课数","red");
        chart.addDatasetBackgroudColor("学期学分数","green");
        chart.addDatasetBackgroudColor("总学分","blue");
        JSONObject cha=chart.build();
        return cha;
    }
    public String getClazzs() {
        List<String> clazzs=studentDao.getClazz();
        JSONObject json=new JSONObject();
        JSONArray clazzlist=new JSONArray();
        for (String s:clazzs){
            clazzlist.add(s);
        }
        json.put("clazz",clazzlist);
        return json.toJSONString();
    }
    public String total(String clazz){
        JSONObject json=new JSONObject();
        //整体数据及格率
        JSONArray gpa=new JSONArray();
        List<Map> gpalist=scoreDao.getNumber(clazz);
        for (Map m:gpalist){
            JSONObject temp=new JSONObject();
            temp.put("name",m.get("name"));
            temp.put("pass",m.get("及格率")+"%");
            temp.put("avg",m.get("平均分"));
            gpa.add(temp);
        }
        json.put("gpa",gpa);
        //整体数据平均分
        List<Map> avglist=scoreDao.getAvg(clazz);
        JSONArray avg=new JSONArray();
        for (Map m:avglist){
            JSONObject temp=new JSONObject();
            temp.put("scorename",m.get("score_name"));
            temp.put("avgnumber",m.get("平均分"));
            avg.add(temp);
        }
        json.put("avg",avg);
        //整体数据科目及格率
        List<Map> passlist=scoreDao.getPass(clazz);
        JSONArray pass=new JSONArray();
        for (Map m:passlist){
            JSONObject temp=new JSONObject();
            temp.put("scorename",m.get("score_name"));
            Float passnumber=((BigDecimal)m.get("及格率")).floatValue();
            temp.put("passnumber",m.get("及格率"));
            pass.add(temp);
        }
        json.put("pass",pass);
        //图表数据
        json.put("picone",getPicone(gpalist));
        json.put("pictwo",getPictwo(clazz));
        json.put("picthree",getThree(clazz));
        json.put("picfour",getFour(passlist));
        json.put("picfive",getFive(avglist));
        return json.toJSONString();
    }

    private JSONObject getFour(List<Map> passlist) {
        ChartJson chart=new ChartJson();
        chart.addDatasetLabel("科目及格率");
        for (Map m:passlist){
            chart.addLabel((String)m.get("score_name"));
            chart.addDatasetFloatData("科目及格率",((BigDecimal)m.get("及格率")).floatValue());
            chart.addDatasetBackgroudColor("科目及格率","blue");
        }
        return chart.build();
    }

    private JSONObject getFive(List<Map> avglist) {
        ChartJson chart=new ChartJson();
        chart.addDatasetLabel("科目平均分");
        for (Map m:avglist){
            chart.addLabel((String)m.get("score_name"));
            chart.addDatasetFloatData("科目平均分",((BigDecimal)m.get("平均分")).floatValue());
            chart.addDatasetBackgroudColor("科目平均分","yellow");
        }
        return chart.build();
    }

    private JSONObject getPictwo(String clazz) {
        List<Map> agelist=studentDao.getAge(clazz);
        ChartJson chart=new ChartJson();
        chart.addDatasetLabel("年龄");
        for (Map m:agelist){
            chart.addLabel((String)m.get("name"));
            chart.addDatasetFloatData("年龄",((BigDecimal)m.get("年龄")).floatValue());
            chart.addDatasetBackgroudColor("年龄","blue");
        }
        return chart.build();
    }

    private JSONObject getThree(String clazz) {
        List<Map> mapList=studentDao.getProvinces(clazz);
        ChartJson json=new ChartJson();
        for (Map m:mapList){
            String key=(String)m.get("province");
            Long value=(Long)m.get("total");
            json.addLabel(key);
            json.addDatasetData(value);
        }
        return json.addDatasetBackgroudColor().build();
    }

    private JSONObject getPicone(List<Map> gpalist) {
        ChartJson chart=new ChartJson();
        chart.addDatasetLabel("及格率").addDatasetLabel("平均分");
        for (Map m:gpalist){
            chart.addLabel((String)m.get("name"));
            chart.addDatasetFloatData("及格率",((BigDecimal)m.get("及格率")).floatValue());
            chart.addDatasetFloatData("平均分",((BigDecimal)m.get("平均分")).floatValue());
            chart.addDatasetBackgroudColor("及格率","red");
            chart.addDatasetBackgroudColor("平均分","blue");
        }
        return chart.build();
    }
}
