package com.qa.webtest.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {

    private static final String TESTDATA_SHEET_PATH="./src/test/resources/testdata/webtestData.xlsx";
    private static Workbook book;

    private static Sheet sheet;

    public static Object[][] getTestData(String sheetName)
    {

        Object data[][]=null;
        try {
            FileInputStream ip= new FileInputStream(TESTDATA_SHEET_PATH);
            try {
               book= WorkbookFactory.create(ip);
               sheet= book.getSheet(sheetName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidFormatException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i=0;i<sheet.getLastRowNum();i++)
        {
            for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
            {
                data[i][j]=sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return data;




    }
}
