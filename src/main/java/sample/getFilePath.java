package sample;

import java.io.File;

public class getFilePath {

    public static File getPath(){

        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        try {
            path = java.net.URLDecoder.decode(path, "UTF-8");//转换处理中文及空格
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return new File(path);
    }
}
