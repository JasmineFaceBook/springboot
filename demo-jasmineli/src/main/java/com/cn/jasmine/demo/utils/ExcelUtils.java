package com.cn.jasmine.demo.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils
{

    /**
     *
     * 根据路径或文件名选择Excel版本
     *
     *
     * @param filePathOrName
     * @param in
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static Workbook chooseWorkbook(String filePathOrName, InputStream in)
            throws IOException
    {
        /** 根据版本选择创建Workbook的方式 */
        Workbook wb = null;
        boolean isExcel2003 = ExcelVersionUtil.isExcel2003(filePathOrName);

        if (isExcel2003)
        {
            wb = new HSSFWorkbook(in);
        }
        else
        {
            wb = new XSSFWorkbook(in);
        }

        return wb;
    }


    static class ExcelVersionUtil
    {

        /**
         *
         * 是否是2003的excel，返回true是2003
         *
         *
         * @param filePath
         * @return
         * @see [类、类#方法、类#成员]
         */
        public static boolean isExcel2003(String filePath)
        {
            return filePath.matches("^.+\\.(?i)(xls)$");

        }

        /**
         *
         * 是否是2007的excel，返回true是2007
         *
         *
         * @param filePath
         * @return
         * @see [类、类#方法、类#成员]
         */
        public static boolean isExcel2007(String filePath)
        {
            return filePath.matches("^.+\\.(?i)(xlsx)$");

        }

    }

    public static final String tempUrl="D:\\test\\fileDemo\\";

    /*
   动态生产多个Excel文件
    */
    public static List<File> getFileList() {
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Test");
            Row row = sheet.createRow(0);
            for (int j = 0; j <= 5; j++) {
                Cell cell = row.createCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue("第" + (j + 1) + "行");
            }
            row = sheet.createRow(1);
            for (int j = 0; j <= 5; j++) {
                Cell cell = row.createCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(j + 1);
            }
            String fileName = "表" + (i + 1) + ".xls";
            File file = new File(fileName);
            try {
                FileOutputStream out = new FileOutputStream(file.getPath());
                workbook.write(out);
                out.close();
            } catch (Exception e) {
                System.out.println("出现异常");
                e.printStackTrace();
            }
            fileList.add(file);
        }
        return fileList;
    }
}
