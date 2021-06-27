package com.cn.jasmine.demo.web;

import com.cn.jasmine.demo.utils.ExcelUtils;
import com.cn.jasmine.demo.utils.ZipUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


@RestController
public class DownLoadApi {

   private static final Logger logger= LoggerFactory.getLogger(DownLoadApi.class);

    @Value("${file.upload}")
    private String fileUpload;


    @RequestMapping(value="/downZip",method={RequestMethod.GET})
    @ResponseBody
    public void downZip(HttpServletResponse res) throws IOException {
      //  List<File> srcfile= ExcelUtils.getFileList();
      //  ZipUtil.zipFiles(srcfile, "压缩文件",res);
        for(int i=0;;i++){
            System.out.println("i:"+i);
        }
    }


    @RequestMapping(value="/downExcel",method={RequestMethod.GET})
    @ResponseBody
    public Object downLoad(HttpServletResponse res){
      return downloadFile(res);
    }

    @RequestMapping(value="/downExcelDiv",method={RequestMethod.GET})
    public void downExcel(HttpServletRequest request,HttpServletResponse response){
        ServletOutputStream output = null;
       try{ HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("财务报盘表");
        //第一行
           HSSFFont font2 =workbook.createFont();
           font2.setFontName("宋体");
           font2.setFontHeightInPoints((short) 10);//设置字体大小  
           font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);//不是粗体显示 
        HSSFCellStyle styleline1 = getStyle(workbook, HSSFColor.BLACK.index,HSSFColor.ROSE.index,HSSFColor.ROSE.index);
        styleline1.setFont(font2);

        //去掉上左右边框
       /* styleline1.setBorderRight(HSSFCellStyle.BORDER_NONE);
        styleline1.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        styleline1.setBorderTop(HSSFCellStyle.BORDER_NONE);
        styleline1.setBorderBottom(HSSFCellStyle.BORDER_NONE);*/
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short) 410);
        //空单元格填充
        for(int i=0;i<10;i++){
            Cell cell2 = row1.createCell((short) i);
            cell2.setCellValue(i);
            cell2.setCellStyle(styleline1);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 0));

        String fileName = "财务报盘表-20200709.xls";
        output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
        workbook.write(output);
        output.flush();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public HSSFCellStyle  getStyle(HSSFWorkbook workbook,short fontColor,short backColor,short ForegroundColor){
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置边框
       /* style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);*/


        // 设置这些样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //设置字体
        HSSFFont font = workbook.createFont();
        font.setColor(fontColor);
        font.setFontHeight((short) 260);
        font.setFontName("宋体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);

        //设值添加背景色
        // style.setFillForegroundColor(ForegroundColor);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillBackgroundColor(backColor);

        style.setFont(font);

        //设置文字自动换行
        style.setWrapText(true);


        return style;

    }


    public String downloadFile(HttpServletResponse res) {
        String realFileName = "example.xlsx";
        File excelFile = new File(fileUpload);
        res.setCharacterEncoding("UTF-8");
        res.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        res.setContentType("application/octet-stream;charset=UTF-8");
        //加上设置大小下载下来的.xlsx文件打开时才不会报“Excel 已完成文件级验证和修复。此工作簿的某些部分可能已被修复或丢弃”
        res.addHeader("Content-Length", String.valueOf(excelFile.length()));
        try {
            res.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(realFileName.trim(), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(fileUpload)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error("【下载模板】{}", e);
                }
            }
        }
        logger.info("【下载模板】成功,exampleFilePath={}", fileUpload);
        return "success";
    }
}
