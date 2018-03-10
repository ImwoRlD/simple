package crawler.HttpRequest;

import Manager.CookiesManager;
import Manager.MemcacheManager;
import Model.Score;
import Uploader.ImageUploader;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.cookie.Cookie;
import utils.ChartJson;
import utils.JSONUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        ChartJson json=new ChartJson();
        json.addLabel("第一个label");
        json.addLabel("第二个label");
        json.addDatasetLabel("dsl1");
        json.addDatasetLabel("dsl2");
        json.addDatasetData("dsl1",213);
        json.addDatasetData("dsl2",31231);
        JSONObject js=json.build();
        System.out.print(js);
    }
}
