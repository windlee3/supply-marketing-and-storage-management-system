package util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorkbookConnection {
    private static Workbook workbook;
    static {
        try {
            FileInputStream fis = new FileInputStream("data.xls");
            workbook = new HSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Workbook getWorkbook() {
        return workbook;
    }

    public static void saveWorkbook() {
        String filePath = "data.xls";
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}