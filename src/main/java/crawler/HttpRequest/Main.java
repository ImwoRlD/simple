package crawler.HttpRequest;

import Uploader.ImageUploader;

public class Main {
    public static void main(String args[]){
        RequestContext context=new RequestContext();
        context.setResponseCharset("GB18030");
        context.url("http://202.194.48.15/validateCodeAction.do")
                .GET();
        HttpRequest request=new HttpRequest();
        request.setRequestContext(context);
        HttpResponse response=request.sendWithCookieStore();
        request.getCookieStore().getCookies();
        System.out.print(response.getBin());
        String result=ImageUploader.upload(response.getBin(),"abcderf");
        System.out.print(result);
    }
}
