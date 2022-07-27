import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class test {

    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load( new FileReader("src\\main\\resources\\config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String showChrome = properties.getProperty("showChrome");

        System.out.println(1);
        if (showChrome.equals("false")){
            System.out.println(1);
        }
    }
}
