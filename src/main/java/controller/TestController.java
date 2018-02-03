package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TestService;

@Controller
@ResponseBody
public class TestController {
    @Autowired
    private TestService testService;
    @RequestMapping(value = "/getcaptcha",method = RequestMethod.GET)
    public String testFetch(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password){
        String url=testService.getcaptcha(username,password);
        return url;
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "captcha") String captcha){
        String result="";
        testService.login(username,password,captcha);
        return result;
    }
}
