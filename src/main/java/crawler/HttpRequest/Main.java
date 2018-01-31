package crawler.HttpRequest;

import Manager.CookiesManager;
import Model.Score;
import Uploader.ImageUploader;
import org.apache.http.cookie.Cookie;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Score score=new Score();
        score.setName("312312");
        score.setRemark("dasdas");
        score.setNumber(1231);
        score.setScore("312312");
        CookiesManager.testsaveString("1",score);
        Score score1=CookiesManager.testloadString("1");
        System.out.print(score1);
    }
}
