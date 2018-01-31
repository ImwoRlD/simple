package crawler.HttpRequest;

import org.apache.http.cookie.Cookie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RequestContext {
    private String requestURL;
    private String RequestMethod;
    private String Charset = "utf-8";
    private String responseCharset;
    private String sessioonId;
    private String proxyAddr;
    private String proxyPort;
    private String uuid;
    private boolean isUpload;
    private boolean isClear;
    private Map<String, String> extraHeaders;
    private Map<String, String> queryParam;
    private Map<String, String> queryForm;
    public Session session;

    public String getSessioonId() {
        return sessioonId;
    }

    public void setSessioonId(String sessioonId) {
        this.sessioonId = sessioonId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public RequestContext() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Object session(String name) {
        if (this.session == null) {
            return null;
        }
        return session.getMap().get("name");
    }

    public void session(String name, Object object) {
        if (this.session == null) {
            this.session = new Session();
        }
        this.session.getMap().put(name, object);
    }

    public RequestContext url(String url) {
        this.requestURL = url;
        return this;
    }

    public RequestContext GET() {
        this.RequestMethod = "GET";
        return this;
    }

    public RequestContext POST() {
        this.RequestMethod = "POST";
        return this;
    }

    public RequestContext header(String name, String value) {
        if (this.extraHeaders == null) {
            this.extraHeaders = new HashMap<>();
        }
        this.extraHeaders.put(name, value);
        return this;
    }

    public RequestContext query(String name, String value) {
        if (this.queryParam == null) {
            this.queryParam = new HashMap<>();
        }
        this.queryParam.put(name, value);
        return this;
    }

    public RequestContext form(String name, String value) {
        if (this.getQueryForm() == null) {
            this.queryForm = new HashMap<>();
        }
        this.queryForm.put(name, value);
        return this;
    }

    public String getResponseCharset() {
        return responseCharset;
    }

    public void setResponseCharset(String responseCharset) {
        this.responseCharset = responseCharset;
    }

    public String getProxyAddr() {
        return proxyAddr;
    }

    public void setProxyAddr(String proxyAddr) {
        this.proxyAddr = proxyAddr;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getRequestMethod() {
        return RequestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        RequestMethod = requestMethod;
    }

    public String getCharset() {
        return Charset;
    }

    public void setCharset(String charset) {
        Charset = charset;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean clear) {
        isClear = clear;
    }

    public Map<String, String> getExtraHeaders() {
        return extraHeaders;
    }

    public void setExtraHeaders(Map<String, String> extraHeaders) {
        this.extraHeaders = extraHeaders;
    }

    public Map<String, String> getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(Map<String, String> queryParam) {
        this.queryParam = queryParam;
    }

    public Map<String, String> getQueryForm() {
        return queryForm;
    }

    public void setQueryForm(Map<String, String> queryForm) {
        this.queryForm = queryForm;
    }

    @Override
    public String toString() {
        return "RequestContext{" +
                "requestURL='" + requestURL + '\'' +
                ", RequestMethod='" + RequestMethod + '\'' +
                ", Charset='" + Charset + '\'' +
                ", proxyAddr='" + proxyAddr + '\'' +
                ", proxyPort='" + proxyPort + '\'' +
                ", isUpload=" + isUpload +
                ", isClear=" + isClear +
                ", extraHeaders=" + extraHeaders +
                ", queryParam=" + queryParam +
                ", queryForm=" + queryForm +
                '}';
    }
}
