package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class Excelutility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    // Constructor to initialize file path
    public Excelutility(String path) {
        this.path = path;
    }

    // Get row count from the sheet
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowCount;
    }
    
    public int getCellCount(String sheetName, int rownum) throws IOException
    {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }


    // Get cell data
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        
        DataFormatter fromatter = new DataFormatter();
        String data;
        
        try {
            data = fromatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        
        workbook.close();
        fi.close();
        return data;
    }

    // Set cell data
    public void setCellData(String sheetName, int rownum, int column, String data) throws IOException {
        File xlfile = new File(path);

        // If file does not exist, create a new one
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
            fo.close();
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        // If sheet does not exist, create a new sheet
        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        // If row does not exist, create a new row
        if (sheet.getRow(rownum) == null) {
            sheet.createRow(rownum);
        }
        row = sheet.getRow(rownum);

        // Create cell and set value
        cell = row.createCell(column);
        cell.setCellValue(data);

        // Write data back to file
        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }
}
