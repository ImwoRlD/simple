package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@Controller
public class UserController {
    @RequestMapping("/login")
    public String login(@RequestParam(value = "user_name", required = true) String username,
                        @RequestParam(value = "user_password", required = true) String userpassword) {
        return null;
    }
}
