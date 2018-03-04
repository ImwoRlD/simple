package Uploader;

import java.io.*;
import java.util.UUID;

public class ImageUploader {
    public static String upload(byte[] data) {
        String uuid= UUID.randomUUID().toString();
        File file = new File("C:\\Users\\Roar\\Desktop\\simple\\target\\simple\\img\\" + uuid + ".jpeg");
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uuid + ".jpeg";
    }
}
