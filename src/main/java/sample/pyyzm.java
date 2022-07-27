package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class pyyzm {


    public static String print(String filename) {

        File path = getFilePath.getPath();
        String parent = path.getParent();

        InputStream inputStream = null;


        try {

            try {
                inputStream = Runtime.getRuntime().exec("cmd /c python3 "+parent+"\\py\\main.py " + filename).getInputStream();
            }catch (IOException e){
                inputStream = Runtime.getRuntime().exec("cmd /c python "+parent+"\\py\\main.py " + filename).getInputStream();
            }

//            inputStream = Runtime.getRuntime().exec("cmd /c python3 C:\\Users\\admin\\Desktop\\java\\jsburp\\one1.8\\src\\main\\java\\sample\\main.py " + filename).getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();

            line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            line = bufferedReader.readLine();


            return line;

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }
}
