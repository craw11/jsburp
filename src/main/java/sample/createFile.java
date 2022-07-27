package sample;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class createFile {

    private static Sheet sheet;
    private static XSSFWorkbook ex;


    public static synchronized XSSFWorkbook getEx() {
        if (ex == null) {
            ex = new XSSFWorkbook();
        }
        return ex;
    }

    public static synchronized Sheet getsheet() {
        if (sheet == null) {
            XSSFWorkbook ex = getEx();
            sheet = ex.createSheet("new");
        }
        return sheet;
    }


}
