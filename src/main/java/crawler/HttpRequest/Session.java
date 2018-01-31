package crawler.HttpRequest;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private Map<String, Object> map = new HashMap<>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
