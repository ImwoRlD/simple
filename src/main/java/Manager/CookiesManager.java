package Manager;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.http.cookie.Cookie;

import java.util.List;

public class CookiesManager {
    private static final CacheManager cacheManager=CacheManager.create("./src/main/resources/ehcache.xml");;

    private CookiesManager() {
    }

    public static List<Cookie> loadCookies(String sessionid) {
        Cache cache = cacheManager.getCache("Cookies");
        Element element=cache.get(sessionid);
        List<Cookie> cookies=(List<Cookie>)element;
        return cookies;
    }
    public static void saveCookies(String sessionId,List<Cookie> cookies){
        Cache cache = cacheManager.getCache("Cookies");
        Element element=new Element(sessionId,cookies);
        cache.put(element);
    }
}
