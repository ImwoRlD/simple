package Manager;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.http.cookie.Cookie;

import java.util.List;

public class CookiesManager {
    private static final CacheManager cacheManager=CacheManager.create("./src/main/resources/ehcache.xml");
    private CookiesManager(){}

    public static CacheManager getCacheManager() {
        return cacheManager;
    }
    public List<Cookie> loadCookies(String uuid){
        Cache cache=cacheManager.getCache("captcha");
        return null;
    }
}
