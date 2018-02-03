package Manager;

import Model.Score;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.http.cookie.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookiesManager {
    private static final CacheManager cacheManager=CacheManager.create("F://captcha//ehcache.xml");
    public static Cookie loadCookies(String sessionid) {
        Cache cache = cacheManager.getCache("Cookies");
        Element element=cache.get(sessionid);
        Cookie cookie=(Cookie)element.getObjectValue();
        return cookie;
    }
    public static void saveCookies(String sessionId,Cookie cookie){
        Cache cache = cacheManager.getCache("Cookies");
        Element element=new Element(sessionId,cookie);
        cache.put(element);
    }
    public static Score testloadString(String id){
        Cache cache = cacheManager.getCache("Cookies");
        Element element=cache.get(id);
        Object obj=element.getObjectValue();
        return (Score) obj;
    }
    public static void testsaveString(String id,Score score){
        Cache cache = cacheManager.getCache("Cookies");
        Element element=new Element(id,score);
        cache.put(element);
    }
    public void destory(){
        cacheManager.shutdown();
    }
}
