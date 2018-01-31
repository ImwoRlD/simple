package crawler.HttpRequest;


import org.apache.http.cookie.Cookie;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class HttpResponse {
    private int code;
    private String charset;
    private Map<String, String> headers;
    private byte[] bin;
    private String imageAddres;
    private List<Cookie> cookies;
    public String string() {
        String str;
        if (charset == null) {
            charset = "utf-8";
        }
        try {
            str = new String(bin, charset);
        } catch (UnsupportedEncodingException e) {
            str = "不支持的编码类型";
        }
        return str;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public byte[] getBin() {
        return bin;
    }

    public void setBin(byte[] bin) {
        this.bin = bin;
    }

    public String getImageAddres() {
        return imageAddres;
    }

    public void setImageAddres(String imageAddres) {
        this.imageAddres = imageAddres;
    }
}
