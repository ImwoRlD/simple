package Manager;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MemcacheManager {
    private static MemcacheManager instance;
    private MemcachedClient client;

    public static MemcacheManager getInstance() {
        if (instance==null){
            instance=new MemcacheManager();
        }
        return instance;
    }
    private MemcacheManager(){
        try {
            client=new XMemcachedClientBuilder(AddrUtil.getAddresses("127.0.0.1:11311")).build();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void set(String key,Object obj){
        try {
            client.set(key,60*5,obj);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Object get(String key){
        try {
            return client.get(key);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void delete(String key){
        try {
            this.client.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
