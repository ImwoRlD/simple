package controller;

import Model.Score;
import com.alibaba.fastjson.JSONObject;
import dao.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private ScoreDao dao;
//    @ResponseBody
//    @RequestMapping(value = "/test")
//    public String test(@RequestParam String name){
//        Map map=dao.getScoreDis(name);
//        String sss="";
//        return null;
//    }
}
