package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class IndexController {
    private static final SimpleDateFormat SDF=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    private static final Date startTime=new Date();
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        JSONObject object=new JSONObject();
        object.put("http-status",200);
        object.put("start-time",SDF.format(startTime));
        object.put("msg","welcome to index");
        object.put("request_time", SDF.format(new Date()));
        object.put("response_time", SDF.format(new Date()));
        return object.toString();
    }
}
