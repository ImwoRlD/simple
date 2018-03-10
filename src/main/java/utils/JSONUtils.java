package utils;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JSONUtils {
    public static JSONObject parseJSONObject(Object obj) {
        JSONObject jsonObject = new JSONObject();
        Method[] methods = obj.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().contains("get") && !m.getName().equals("getClass")) {
                try {
                    String key = m.getName().replaceAll("get", "").toLowerCase();
                    String value = m.invoke(obj) + "";
                    jsonObject.put(key, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }
}
