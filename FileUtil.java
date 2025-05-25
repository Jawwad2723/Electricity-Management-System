package com.mycompany.project;
import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static void emptyFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
