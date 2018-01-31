package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.TestService;

@Controller
public class TestController {
    @Autowired
    private TestService testService;
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String testFetch(@RequestParam(value = "user_name", required = true) String username,
                            @RequestParam(value = "user_password", required = true) String password,
                            @RequestParam(value = "session_id",required = true) String sessionId){
        testService.fetch(username,password,sessionId);
    }
}
