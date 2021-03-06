package crawler.HtmlParser;

import Model.Score;
import Model.StudentInfo;
import Model.Term;
import Model.TimeScore;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.CastUtil;
import utils.ParseUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HtmlParser {
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public StudentInfo parseStudentInfo(String infohtml) {
        StudentInfo studentInfo = new StudentInfo();
        Element infotable = Jsoup.parse(infohtml).select("table#tblView").get(0);
        try {
            String loginName = infotable.select("tr").get(0).select("td").get(1).text();
            String password = "";
            String name = infotable.select("tr").get(0).select("td").get(3).text();
            Date birthDate = dateFormat.parse(infotable.select("tr").get(6).select("td").get(3).text());
            String sex = infotable.select("tr").get(3).select("td").get(1).text();
            String province = infotable.select("tr").get(7).select("td").get(3).text();
            String classId = infotable.select("tr").get(14).select("td").get(3).text();
            String major = infotable.select("tr").get(13).select("td").get(1).text();
            String nation = infotable.select("tr").get(5).select("td").get(3).text();
            studentInfo.setLoginName(loginName);
            studentInfo.setPassword(password);
            studentInfo.setNation(nation);
            studentInfo.setBirthDate(birthDate);
            studentInfo.setSex(sex);
            studentInfo.setProvince(province);
            studentInfo.setClassId(classId);
            studentInfo.setMajor(major);
            studentInfo.setName(name);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return studentInfo;
    }

    public List<TimeScore> parseStudentScore(String passhtml, String falihtml) {
        Map<Term, TimeScore> map = new HashMap<>();
        addPassScore(map, passhtml);
        addFailSocre(map, falihtml);
        List<TimeScore> list = new ArrayList<>();
        list.addAll(map.values());
        return list;
    }

    private void addPassScore(Map<Term, TimeScore> map, String passhtml) {
        Document document = Jsoup.parse(passhtml);
        Elements tableHead = document.select("table#tblHead");
        Elements tableTop = document.select("table.titleTop2");
        for (int i = 0; i < tableHead.size(); i++) {
            String termStr = tableHead.get(i).select("b").text();
            Elements scoreTrs = tableTop.get(i).select("table#user").select("tbody").select("tr");
            Term term = new Term();
            term.setText(termStr.substring(0,9));
            term.setTermNum(ParseUtil.parseTermNo(termStr));
            for (int j = 0; j < scoreTrs.size(); j++) {
                Element tr = scoreTrs.get(j);
                Score score = new Score();
                String name = tr.select("td").get(2).text();
                Float point = Float.parseFloat(tr.select("td").get(4).text());
                String type = tr.select("td").get(5).text();
                String sco = tr.select("td").get(6).text();
                score.setName(name);
                score.setPoint((int) (point * 100));
                score.setType(type);
                score.setScore(sco);
                score.setNumber(ParseUtil.parseNumScore(sco));
                score.setRemark("无");
                CastUtil.addScoreToScoreMap(map, term, score);
            }
        }
    }

    private void addFailSocre(Map<Term, TimeScore> map, String failhtml) {
        Document document = Jsoup.parse(failhtml);
        Elements tables = document.select("table.titleTop2");
        for (Element table : tables) {
            Elements trs = table.select("table#user").select("tbody").select("tr");
            for (int i = 0; i < trs.size(); i++) {
                Score score = new Score();
                String termStr = trs.get(i).select("td").get(7).text();
                Term term = ParseUtil.str2Term(termStr);
                String name = trs.get(i).select("td").get(2).text();
                String type = trs.get(i).select("td").get(5).text();
                String sco = trs.get(i).select("td").get(6).text();
                score.setName(name);
                score.setType(type);
                score.setScore(sco);
                score.setPoint(0);
                score.setNumber(ParseUtil.parseNumScore(sco));
                score.setRemark("不及格");
                for (Term temp:map.keySet()){
                    TimeScore timeScore=map.get(temp);
                    List<Score> list=timeScore.getScoreList();
                    for (int k=0;k<list.size();k++){
                        Score tempscore=list.get(k);
                        if (tempscore.getName().equals(score.getName())){
                            tempscore.setRemark("重修");
                        }
                    }
                }
                CastUtil.addScoreToScoreMap(map, term, score);
            }
        }
    }
}
