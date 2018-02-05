package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.FetchService;

@RestController(value = "/fetch")
public class FetchController {
    @Autowired
    private FetchService fetchService;
    @RequestMapping(value = "/getCaptcha")
    public String fetchGetCaptcha(@RequestParam(value = "sessionID") String sessionId){
        return fetchService.getCaptcha(sessionId);
    }
    @RequestMapping(value = "/start")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "captcha") String captcha,
                        @RequestParam(value = "sessionId") String sessionId){
        return fetchService.loginAndFetch(username,password,captcha,sessionId);
    }
}
