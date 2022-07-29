package sample;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class actionocr {


    File path = getFilePath.getPath();
    String parent = path.getParent();

    InputStream inputStream = null;

    public void actionocr(String port) {
        try {
            inputStream = Runtime.getRuntime().exec("cmd /k start python3 C:\\Users\\admin\\Desktop\\ocr_api_server-main\\ocr_server.py --port "+port+" --ocr").getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
