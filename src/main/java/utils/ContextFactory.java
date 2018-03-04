package utils;

import crawler.HttpRequest.RequestContext;

public class ContextFactory {
    public static RequestContext getContext(String method,String... str){
        RequestContext requestContext=new RequestContext();
        switch (method){
            case "getcaptcha":
                requestContext.setSessioonId(str[0]);
                requestContext.setClear(true);
                requestContext.GET().url(CastUtil.buildUrl("captcha")).setUpload(true);
                break;
            case "login":
                requestContext.setSessioonId(str[0]);
                requestContext.setResponseCharset("gb2312");
                requestContext.POST().url(CastUtil.buildUrl("login"))
                        .form("zjh",str[1])
                        .form("mm",str[2])
                        .form("v_yzm",str[3]);
                break;
            case "info":
                requestContext.setSessioonId(str[0]);
                requestContext.setResponseCharset("gb2312");
                requestContext.GET().url(CastUtil.buildUrl("info"));
                break;
            case "pass":
                requestContext.setSessioonId(str[0]);
                requestContext.setResponseCharset("gb2312");
                requestContext.GET().url(CastUtil.buildUrl("pass"));
                break;
            case "fail":
                requestContext.setSessioonId(str[0]);
                requestContext.setResponseCharset("gb2312");
                requestContext.GET().url(CastUtil.buildUrl("fail"));
                break;
            default:
                break;
        }
        return requestContext;
    }
}
