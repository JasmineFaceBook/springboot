/*


import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

public class A extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("abc.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 初始化
        SmartUpload smartUpload = new SmartUpload();
        ServletConfig config = this.getServletConfig();
        smartUpload.initialize(config, request, response);
        try {
            // 上传文件
            smartUpload.upload();
            // 得到上传的文件对象
            com.jspsmart.upload.File smartFile = smartUpload.getFiles().getFile(0);
            String name = smartFile.getFileName();
            // 保存文件
            smartFile.saveAs("/picture/" + name, SmartUpload.SAVE_AUTO);
            // 传过来的注册数据
            // 只需要new SmartUpload().getRequest().getParameter(""))就能获取到相应的表单数据
            String stuname = smartUpload.getRequest().getParameter("stuname");
            int stuno = Integer.parseInt(smartUpload.getRequest().getParameter("stuno"));
            System.out.println("name:  " + stuname + "   stuno:  " + stuno);
            response.sendRedirect("abc.jsp");
        } catch (SmartUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
*/
