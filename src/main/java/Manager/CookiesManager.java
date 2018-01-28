package Manager;

import net.sf.ehcache.CacheManager;

public class CookiesManager {
    private CacheManager cacheManager=CacheManager.create("./src/main/resources/ehcache.xml");
    private CookiesManager(){}

    public CacheManager getCacheManager() {
        return cacheManager;
    }
}
