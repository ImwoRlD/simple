package Uploader;

import java.io.*;

public class ImageUploader {
    public static String upload(byte[] data, String uuid) {
        File file = new File("C:\\Users\\Roar\\Desktop\\simple\\src\\main\\webapp\\img\\" + uuid + ".jpeg");
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "C:\\Users\\Roar\\Desktop\\simple\\src\\main\\webapp\\img\\" + uuid + ".jpeg";
    }
}
