package crawler.HttpRequest;

import Uploader.ImageUploader;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    private HttpRequestBase requestBase;
    private RequestContext requestContext;
    private CookieStore cookieStore=new BasicCookieStore();

    public CookieStore getCookieStore() {
        return cookieStore;
    }
    public void setRequestContext(RequestContext context){
        this.requestContext=context;
    }
    public void init(){
        switch (requestContext.getRequestMethod()){
            case "GET":
                requestBase=new HttpGet(getRequestUrl());
                break;
            case "POST":
                requestBase=new HttpPost(getRequestUrl());
                addRequestBody();
                break;
            default:
                requestBase=new HttpGet(getRequestUrl());
                break;
        }
    }
    public HttpResponse sendWithCookieStore(){
        HttpResponse httpResponse=new HttpResponse();
        if(requestContext.isClear()){
            cookieStore.clear();
        }
        CloseableHttpClient httpClient=HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        this.init();
        this.addRequestHeaders();
        try {
            CloseableHttpResponse response=httpClient.execute(this.requestBase);
            httpResponse.setCharset(requestContext.getResponseCharset());
            httpResponse.setCode(response.getStatusLine().getStatusCode());
            httpResponse.setBin(EntityUtils.toByteArray(response.getEntity()));
            if (requestContext.isUpload()){
                ImageUploader.upload(httpResponse.getBin(),requestContext.getUuid());
            }
            httpResponse.setHeaders(new HashMap<String,String>());
            for (Header header:response.getAllHeaders()){
                httpResponse.getHeaders().put(header.getName(),header.getValue());
            }
            response.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return httpResponse;
    }
    public String getRequestUrl(){
        StringBuilder baseUrl=new StringBuilder(requestContext.getRequestURL());
        if (requestContext.getQueryParam()!=null){
            baseUrl=baseUrl.append("?");
            Map<String,String> queryMap=requestContext.getQueryParam();
            for (String name:queryMap.keySet()){
                try {
                    baseUrl.append(URLEncoder.encode(name,requestContext.getCharset()))
                            .append("=")
                            .append(URLEncoder.encode(queryMap.get(name),requestContext.getCharset()))
                            .append("&");
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
        }
        return baseUrl.toString();
    }
    public void addRequestHeaders(){
        if (requestContext.getExtraHeaders()!=null){
            for (String key:requestContext.getExtraHeaders().keySet()){
                requestBase.addHeader(key,requestContext.getExtraHeaders().get(key));
            }
        }
    }
    public void addRequestBody(){
        List<BasicNameValuePair> list=new ArrayList<>();
        if (requestContext.getQueryForm()!=null){
            for (String key:requestContext.getQueryForm().keySet()){
                list.add(new BasicNameValuePair(key,requestContext.getQueryForm().get(key)));
            }
        }
        try {
            UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(list,requestContext.getCharset());
            ((HttpPost)requestBase).setEntity(formEntity);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public HttpResponse sendByProxy(){
        return null;
    }
}
