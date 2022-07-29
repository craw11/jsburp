package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.UUID;

public class Controller {


    @FXML
    private Button chooserpass;

    @FXML
    private Button chooserpass1;

    @FXML
    private Button chooseuser;

    @FXML
    private Button chooseuser1;

    @FXML
    private TextField denglu;

    @FXML
    private TextField denglu1;

    @FXML
    private Button logintext;

    @FXML
    private Button logintext1;



    @FXML
    private TextField passtext;

    @FXML
    private TextField passtext1;

    @FXML
    private Label showaction;

    @FXML
    private TextField threadnum;

    @FXML
    private TextField threadnum1;

    @FXML
    private TextField urltext;

    @FXML
    private TextField urltext1;

    @FXML
    private TextField usertxt;

    @FXML
    private TextField usertxt1;

    @FXML
    private TextField yzimg;

    @FXML
    private TextField yzimg1;

    @FXML
    private TextField yztext;

    @FXML
    private TextField yztext1;

    @FXML
    private TextField port;


    @FXML
    void login(ActionEvent event) {

        String usertxtText = usertxt.getText();
        String passtextText = passtext.getText();
        String urltextText = urltext.getText();
        String yzimgText = yzimg.getText();
        String yztextText = yztext.getText();
        String dengluText = denglu.getText();


        int threadnumText = Integer.parseInt(threadnum.getText());

        File path = getFilePath.getPath();
        String parent = path.getParent();
        if (getAccount.user.size() == 0) {
            getAccount.setUser(parent + "/dict/user.txt");
//            getAccount.setUser("src/main/resources/user.txt");
        }

        if (getAccount.password.size() == 0) {
            getAccount.setPass(parent + "/dict/pass.txt");
//            getAccount.setPass("src/main/resources/pass.txt");
        }

        long msb = System.currentTimeMillis();
        long lsb = System.currentTimeMillis();
        UUID uuidConstructor = new UUID(msb, lsb);
        String replace = uuidConstructor.toString().replace("-", "");


        for (int i = 0; i < threadnumText; i++) {

            if (yzimgText.equals("")) {
                openchrome2 openchrome = new openchrome2(urltextText, usertxtText, passtextText, dengluText, threadnumText, i, replace);
                Thread thread = new Thread(openchrome);
                thread.start();
            } else {
                openchrome openchrome = new openchrome(urltextText, usertxtText, passtextText, yzimgText, yztextText, dengluText, threadnumText, i, replace);
                Thread thread = new Thread(openchrome);
                thread.start();
            }
        }
    }

    @FXML
    void login1(ActionEvent event) {

        String usertxtText = usertxt1.getText();
        String passtextText = passtext1.getText();
        String urltextText = urltext1.getText();
        String yzimgText = "";
        int k;
        if (yzimg1.getText().equals("")) {
            k = 0;
        } else {
            k = Integer.parseInt(yzimg1.getText());
        }

        for (int j = 0; j < k; j++) {
            yzimgText = yzimgText + j;
        }


        String yztextText = yztext1.getText();
        String dengluText = denglu1.getText();

        int threadnumText = Integer.parseInt(threadnum1.getText());

        File path = getFilePath.getPath();
        String parent = path.getParent();
        if (getAccount.user.size() == 0) {
            getAccount.setUser(parent + "/dict/user.txt");
//            getAccount.setUser("src/main/resources/user.txt");
        }

        if (getAccount.password.size() == 0) {
            getAccount.setPass(parent + "/dict/pass.txt");
//            getAccount.setPass("src/main/resources/pass.txt");
        }


        long msb = System.currentTimeMillis();
        long lsb = System.currentTimeMillis();
        UUID uuidConstructor = new UUID(msb, lsb);
        String replace = uuidConstructor.toString().replace("-", "");


        for (int i = 0; i < threadnumText; i++) {

            if (yztextText.equals("")) {
                baopochrome2 openchrome = new baopochrome2(urltextText, usertxtText, passtextText, dengluText, threadnumText, i, replace);
                Thread thread = new Thread(openchrome);
                thread.start();
            } else {
                baopochrome openchrome = new baopochrome(urltextText, usertxtText, passtextText, yztextText, yzimgText, dengluText, threadnumText, i, replace);
                Thread thread = new Thread(openchrome);
                thread.start();
            }
        }
    }


    @FXML
    void userfile(ActionEvent event) {

        Stage fileStage = null;
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(fileStage);
        fileChooser.setTitle("Choose file");

        String path = selectedFile.getPath();
        getAccount.setUser(path);
        System.out.println(path);
    }

    @FXML
    void passfile(ActionEvent event) {

        Stage fileStage = null;
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(fileStage);
        fileChooser.setTitle("Choose file");

        String path = selectedFile.getPath();
        getAccount.setPass(path);
        System.out.println(path);
    }

    @FXML
    void actionocr(ActionEvent event) {

        new ocroptions().setOCRPORT(port.getText());
        new actionocr().actionocr(ocroptions.getOCRPORT());

    }

    @FXML
    void checkocr(ActionEvent event) throws Exception {

        String s = checkpy.doGet("http://127.0.0.1:"+ocroptions.getOCRPORT()+ "/ping");
        if (s.equals("pong")){
            showaction.setText("连接成功");
        } else {
            showaction.setText("连接失败");
        }


    }
}
