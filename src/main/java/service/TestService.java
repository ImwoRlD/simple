package service;

import crawler.HttpRequest.RequestContext;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String fetch(String username,String password,String sessionId){
        RequestContext requestContext=new RequestContext();
        requestContext.url("http://202.207.177.39:8088/validateCodeAction.do")
    }
}
