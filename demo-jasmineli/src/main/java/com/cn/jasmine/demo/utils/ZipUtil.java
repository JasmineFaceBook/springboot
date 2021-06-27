package com.cn.jasmine.demo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    private static final Log log = LogFactory.getLog(ZipUtil.class);

    @Value("${file.upload}")
    public static  String fileUrl;
    /**
     * 压缩文件
     *
     * @param srcfile File[] 需要压缩的文件列表
     * @param zipName File 压缩文件名称
     */
    public static void zipFiles(List<File> srcfile,String zipName , HttpServletResponse response) {

        File zipfile=new File(fileUrl+zipName+".zip");

        byte[] buf = new byte[1024];
        try {
            // Create the ZIP file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            // Compress the files
            for (int i = 0; i < srcfile.size(); i++) {
                File file = srcfile.get(i);
                FileInputStream in = new FileInputStream(file);
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(file.getName()));
                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Complete the entry
                out.closeEntry();
                in.close();
            }
            // Complete the ZIP file
            out.close();

            downloadFile(zipfile,response);
        } catch (IOException e) {
            log.error("ZipUtil zipFiles exception:" + e);
        }
    }

    /*
   下载文件
    */
    public static void downloadFile(File file, HttpServletResponse response) throws IOException {
        String fileName = file.getName();
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/force-download"); // 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName); // 设置文件名
            response.setHeader("Content-Length", String.valueOf(file.length()));
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println("下载出现异常");
            e.printStackTrace();
        } finally {
            fis.close();
        }
    }



    /**
     * 解压缩
     *
     * @param zipfile File 需要解压缩的文件
     * @param descDir String 解压后的目标目录
     */
    public static void unZipFiles(File zipfile, String descDir) {
        try {
            // Open the ZIP file
            ZipFile zf = new ZipFile(zipfile);
            for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
                // Get the entry name
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String zipEntryName = entry.getName();
                InputStream in = zf.getInputStream(entry);
                // System.out.println(zipEntryName);
                OutputStream out = new FileOutputStream(descDir + zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                // Close the file and stream
                in.close();
                out.close();
            }
        } catch (IOException e) {
            log.error("ZipUtil unZipFiles exception:" + e);
        }
    }
}
