package controller;

import Model.StudentInfo;
import com.alibaba.fastjson.JSONObject;
import dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DataService;
import service.TestService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private DataService dataService;
    @RequestMapping(value = "/datafetch",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String datafetch(HttpServletRequest request,
                            HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        String[] temp=cookies[0].getValue().split("&");
        JSONObject json=new JSONObject();
        if (testService.checkStudentInfo(temp[1])){
            json.put("msg","个人信息已经存在,请返回首页登录查看");
            return json.toString();
        }
        Long begin=System.currentTimeMillis();
        testService.fetchData(temp[0],temp[1],temp[2],temp[3]);
        Long end=System.currentTimeMillis();
        json.put("msg","爬取成功,耗时"+(end-begin)/1000+"s,请返回首页登录查看");
        return json.toString();
    }
    @RequestMapping(value = "/persondata",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String test(HttpServletRequest request,@RequestParam(value = "page") int n){
        Cookie[] cookies=request.getCookies();
        String username=cookies[0].getValue();
        String json=dataService.personData(username,n);
        return json;
    }
    @RequestMapping(value = "/getscore",method =RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String page(HttpServletRequest request,@RequestParam(value = "page") int n){
        Cookie[] cookies=request.getCookies();
        String username=cookies[0].getValue();
        String json=dataService.pageScore(username,n);
        return json;
    }
    @RequestMapping(value = "/getClazz",method =RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getclazzs(){
        String json=dataService.getClazzs();
        return json;
    }
    @RequestMapping(value = "/clazz",method =RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSel(@RequestParam(value = "clazz") String clazz){
        return dataService.total(clazz);
    }
}
