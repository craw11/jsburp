package sample;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class actionocr {


    File path = getFilePath.getPath();
    String parent = path.getParent();

    InputStream inputStream = null;

    public void actionocr(String port, boolean selected) {

        if (selected) {
            try {
                inputStream = Runtime.getRuntime().exec("cmd /k start " + parent + "\\py\\python38\\python.exe " + parent + "\\py\\ocr_server.py --port " + port + " --ocr").getInputStream();
            } catch (IOException e) {
                try {
                    inputStream = Runtime.getRuntime().exec("cmd /k start python " + parent + "\\py\\ocr_server.py --port " + port + " --ocr").getInputStream();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else {
            try {
                inputStream = Runtime.getRuntime().exec("cmd /k start " + parent + "\\py\\python38\\python.exe " + parent + "\\py\\ocr_server.py --port " + port + " --ocr --old").getInputStream();
            } catch (IOException e) {
                try {
                    inputStream = Runtime.getRuntime().exec("cmd /k start python " + parent + "\\py\\ocr_server.py --port " + port + " --ocr --old").getInputStream();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }


}
