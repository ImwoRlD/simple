package crawler.HttpRequest;

import Manager.CookiesManager;
import Manager.MemcacheManager;
import Model.Score;
import Uploader.ImageUploader;
import org.apache.http.cookie.Cookie;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Score score=new Score();
        score.setNumber(1231);
        score.setName("213213");
        score.setType("321321");
        MemcacheManager.getInstance().set("score",score);
        Score score1=(Score) MemcacheManager.getInstance().get("score");
        System.out.print(score1);
    }
}
