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
    @RequestMapping(value = "/getcaptcha",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getCaptcha(@RequestParam(value = "sessionId") String sessionId){
        JSONObject json=new JSONObject();
        String url=testService.getcaptcha(sessionId);
        json.put("url",url);
        return json.toString();
    }
    @RequestMapping(value = "/datafetch",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String datafetch(@RequestParam(value = "sessionId") String sessionId,
                        @RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "captcha") String captcha){
        JSONObject json=new JSONObject();
        if (testService.checkStudentInfo(username)){
            json.put("msg","个人信息已经存在,请返回首页登录查看");
            return json.toString();
        }
        Long begin=System.currentTimeMillis();
        testService.fetchData(sessionId,username,password,captcha);
        Long end=System.currentTimeMillis();
        json.put("msg","爬取成功,耗时"+(end-begin)/1000+"s,请返回首页登录查看");
        return json.toString();
    }
    @RequestMapping(value = "/persondata",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String test(HttpServletRequest request,@RequestParam(value = "page") int n){
//        String username=request.getCookies()[0].getValue();
        String username="20142206453";
        String json=dataService.personData(username,n);
        return json;
    }
    @RequestMapping(value = "/getscore",method =RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String page(HttpServletRequest request,@RequestParam(value = "page") int n){
        String username="20142206453";
        String json=dataService.pageScore(username,n);
        return json;
    }
}
