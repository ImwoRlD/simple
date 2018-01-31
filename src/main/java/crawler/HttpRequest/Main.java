package crawler.HttpRequest;

import Manager.CookiesManager;
import Uploader.ImageUploader;
import org.apache.http.cookie.Cookie;

import java.util.List;

public class Main {
    public static void main(String args[]) {
        RequestContext context=new RequestContext();
        context.GET().url("http://www.baidu.com");
        HttpRequest httpRequest=new HttpRequest(context);
        HttpResponse response=httpRequest.sendWithCookieStore();
        String html=response.string();
        List<Cookie> cookies=httpRequest.getCookieStore().getCookies();
        String ss="";
    }
}
