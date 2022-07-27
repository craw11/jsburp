package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class getAccount {

    public static ArrayList<String> user = new ArrayList<>();
    public static ArrayList<String> password = new ArrayList<>();


    public static void setUser(String userst) {

        File userdict = new File(userst);

        FileInputStream inputStream = null;

        try {

            inputStream = new FileInputStream(userdict);
//            inputStream = new FileInputStream(userst);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;
            int len = 0;
            //设置的读取的字节数
            while ((line = br.readLine()) != null) {

                user.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void setPass(String passst) {

        File passdict = new File(passst);

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(passdict);
//            inputStream = new FileInputStream(passst);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(inputStream));

            String line2 = null;
            int len2 = 0;
            //设置的读取的字节数
            while ((line2 = br2.readLine()) != null) {

                password.add(line2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
