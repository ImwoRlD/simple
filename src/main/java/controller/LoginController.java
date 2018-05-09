package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TestService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    TestService testService;
    @RequestMapping(value = "/login/getcaptcha",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getCaptcha(@RequestParam(value = "sessionId") String sessionId){
        JSONObject json=new JSONObject();
        String url=testService.getcaptcha(sessionId);
        json.put("url",url);
        return json.toString();
    }
    @RequestMapping(value = "/login/submit",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(@RequestParam(value = "sessionId") String sessionId,
                        @RequestParam(value = "username") String username,
                        @RequestParam(required = false,value = "password") String password,
                        @RequestParam(required = false,value = "captcha") String captcha,
                        @RequestParam(value = "type") String type,
                        HttpServletRequest request,
                        HttpServletResponse response){
        if (type.equals("option1")){
            Cookie[] cookies = request.getCookies();
            if (cookies!=null) {
                for(Cookie cookie : cookies){
                    cookie.setMaxAge(0);
                }
            }
            Cookie cookie=new Cookie("param",sessionId+"&"+username+"&"+password+"&"+captcha);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "demo";
        }
        if (type.equals("option2")){
            Cookie[] cookies = request.getCookies();
            if (cookies!=null) {
                for(Cookie cookie : cookies){
                    cookie.setMaxAge(0);
                }
            }
            Cookie cookie=new Cookie("param",username);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "person";
        }
        return null;
    }
}
