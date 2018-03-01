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
import service.TestService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController {
    @Autowired
    private TestService testService;
    @RequestMapping(value = "/getcaptcha",method = RequestMethod.GET)
    @ResponseBody
    public String testFetch(HttpServletRequest request,HttpServletResponse response,
                            @RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password){
        JSONObject json=new JSONObject();
        String url=testService.getcaptcha(username,password);
        json.put("url",url);
        return json.toString();
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "captcha") String captcha){
        String result="";
        testService.login(username,password,captcha);
        return result;
    }
    @RequestMapping(value = "/test")
    public String test(){
        return "";
    }
}
