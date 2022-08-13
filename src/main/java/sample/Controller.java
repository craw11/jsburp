package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.jar.JarOutputStream;

public class Controller {

    @FXML
    private TextField denglu;

    @FXML
    private TextField denglu1;

    @FXML
    private Button logintext;

    @FXML
    private Slider logintext1;

    @FXML
    private ToggleButton openchrome;

    @FXML
    private TextField passtext;

    @FXML
    private TextField passtext1;

    @FXML
    private Slider threadnum;

    @FXML
    private Slider threadnum1;

    @FXML
    private ToggleGroup toggle1;

    @FXML
    private ToggleGroup toggle12;

    @FXML
    private ToggleGroup toggle13;

    @FXML
    private ToggleGroup toggle14;

    @FXML
    private ToggleGroup toggle15;

    @FXML
    private ToggleGroup toggle17;

    @FXML
    private ToggleGroup toggle19;

    @FXML
    private ToggleGroup toggle2;

    @FXML
    private ToggleGroup toggle20;

    @FXML
    private ToggleGroup toggle3;

    @FXML
    private ToggleGroup toggle4;

    @FXML
    private ToggleGroup toggle5;

    @FXML
    private TextField urltext;

    @FXML
    private TextField urltext1;

    @FXML
    private TextField yztext;
    @FXML
    private TextField usertxt;

    @FXML
    private TextField usertxt1;

    @FXML
    private TextField yzimg;
    @FXML
    private ChoiceBox<String> yznum;

    @FXML
    private ChoiceBox<String> yzimg1;

    @FXML
    private TextField yztext1;

    @FXML
    private ToggleButton yzmnew;

    @FXML
    private ToggleButton yzmold;

    @FXML
    private TextField port;

    @FXML
    private Label showaction;


    @FXML
    private RadioButton xpath;
    @FXML
    private RadioButton css;
    @FXML
    private void initialize() {
        yznum.setValue("4");
        yznum.getItems().add("1");
        yznum.getItems().add("2");
        yznum.getItems().add("3");
        yznum.getItems().add("4");
        yznum.getItems().add("5");
        yznum.getItems().add("6");
        yznum.getItems().add("7");
        yznum.getItems().add("8");
        yzimg1.setValue("4");
        yzimg1.getItems().add("1");
        yzimg1.getItems().add("2");
        yzimg1.getItems().add("3");
        yzimg1.getItems().add("4");
        yzimg1.getItems().add("5");
        yzimg1.getItems().add("6");
        yzimg1.getItems().add("7");
        yzimg1.getItems().add("8");
    }

    @FXML
    void login(ActionEvent event) throws IOException {

        String usertxtText = usertxt.getText().replace(" ","");
        String passtextText = passtext.getText().replace(" ","");
        String urltextText = urltext.getText().replace(" ","");
        String yzimgText = yzimg.getText().replace(" ","");
        String yztextText = yztext.getText().replace(" ","");
        String dengluText = denglu.getText().replace(" ","");


        chromeoptions.openchrome = openchrome.isSelected();
        chromeoptions.isxpath = xpath.isSelected();
        int threadnumText = Integer.parseInt(String.valueOf(threadnum.getValue()).substring(0,2).replace(".",""));

        String value = yznum.getValue();
        yzmconfig.num = Integer.parseInt(value);

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

        outFile.num = threadnumText;
        ArrayList<HarEntry> entries = new ArrayList<>();
        outFile.entries = entries;

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

        String usertxtText = usertxt1.getText().replace(" ","");
        String passtextText = passtext1.getText().replace(" ","");
        String urltextText = urltext1.getText().replace(" ","");
        String yzimgText = "";
        int k;
        if (yzimg1.getValue().equals("")) {
            k = 0;
        } else {
            k = Integer.parseInt(yzimg1.getValue());
        }

        for (int j = 0; j < k; j++) {
            yzimgText = yzimgText + j;
        }


        String yztextText = yztext1.getText().replace(" ","");
        String dengluText = denglu1.getText().replace(" ","");

        chromeoptions.openchrome = openchrome.isSelected();
        chromeoptions.isxpath = xpath.isSelected();
        int threadnumText = Integer.parseInt(String.valueOf(threadnum1.getValue()).substring(0,2).replace(".",""));

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

        outFile.num = threadnumText;
        ArrayList<HarEntry> entries = new ArrayList<HarEntry>();
        outFile.entries = entries;

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
    void actionocr(ActionEvent event) throws Exception {


        new ocroptions().setOCRPORT(port.getText());

        new actionocr().actionocr(ocroptions.getOCRPORT(),yzmnew.isSelected());

        String s = checkpy.doGet("http://127.0.0.1:" + ocroptions.getOCRPORT() + "/ping");
        if (s.equals("pong")) {
            showaction.setText("连接成功");
        } else {
            showaction.setText("连接失败");
        }

    }

    @FXML
    void checkocr(ActionEvent event) throws Exception {

        String s = checkpy.doGet("http://127.0.0.1:" + ocroptions.getOCRPORT() + "/ping");
        if (s.equals("pong")) {
            showaction.setText("连接成功");
        } else {
            showaction.setText("连接失败");
        }
    }

}
