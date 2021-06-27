package com.cn.jasmine.demo.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 文件上传
 *
 * @author libingyao
 * @create 2020-02-15-18:45
 */
@RestController
public class UploadApi {

    @Value("${file.upload}")
    private String fileUpload;


    @Value("${file.upload.enterWord}")
    private String fileUploadEnterWord;


    @RequestMapping(value="/upload",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object upload(@RequestParam(value="inputFile",required=false) MultipartFile inputFile,
                         @RequestParam("path")String path,@RequestParam("password")String password, HttpServletResponse response){
        System.out.println("fileName:"+inputFile.getOriginalFilename());
        response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域访问报错

        Map map=new HashMap<String,String>();
        map.put("code",0);
        map.put("message","上传失败!");
        if(!StringUtils.isEmpty(fileUploadEnterWord)){
            Map<Integer, String> eszMap = new HashMap<Integer , String>();//解密
            if(password!=null && (!"".equals(password))){
                eszMap = apply(password);
                password=eszMap.get(0);
            }
            //匹配密码
            if(!password.equals(fileUploadEnterWord)){
                map.put("message","上传口令错误!");
                return map;
            }
        }
        if(null==inputFile.getOriginalFilename()||inputFile.getOriginalFilename().equals("")){
            map.put("message","上传文件不能为空!");
            return map;
        }
        if(StringUtils.isEmpty(path)||"".equals(path)){
            map.put("message","上传路径不能为空!");
            return map;
        }else if(!path.endsWith("/")){
            map.put("message","上传路径必须为‘/’结尾为空!");
            return map;
        }

        try {
            InputStream in = inputFile.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            File file = new File(path);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            String fileName=new String(inputFile.getOriginalFilename().getBytes("UTF-8"), "UTF-8");
            System.out.println("url:"+path+fileName);
            OutputStream out = new FileOutputStream(path+fileName);
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            System.out.println("----------" + path+inputFile.getOriginalFilename() +"文件上传失败————————");
            e.printStackTrace();
            return "上传失败!";
        }
        map.put("code",1);
        map.put("message","上传成功!");
        return map;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){

        System.out.println("fileUrl:"+fileUploadEnterWord);
        return "文件上传成功！";
    }

    @GetMapping("/dir")
    @ResponseBody
    public Object getFile(@RequestParam("path")String path){
        File file=new File(path);
        String []files=file.list();
        List<JSONObject> list=new ArrayList<>();

        if(null!=files) {
            for (int i = 0; i < files.length; i++) {
                JSONObject jsonpObject = new JSONObject();
                jsonpObject.put("path", path);
                jsonpObject.put("fileName", files[i]);
                list.add(jsonpObject);
            }
        }
        System.out.println("fileUrl:"+path+",fileList:"+list);
        return list;
    }

    public static Map<Integer, String> apply(String code) {
        try {
            Map<Integer, String> context = new HashMap<Integer, String>();
            List<String> partList = new ArrayList<String>(Arrays.asList(code.substring(4).split("%")));
            for(int index = 0, key = Integer.parseInt(partList.remove(partList.size() - 1)); read(index, key, partList, context); ++index);
            return context;
        } catch(Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    private static boolean read(int index, int key, List<String> partList, Map<Integer, String> context) {
        if(partList.size() <= 0) return false;
        StringBuilder sb = new StringBuilder();
        for(int i = 0, length = nextElement(key, partList); i < length; ++i) {
            sb.append(Character.toChars(nextElement(key, partList)));
        }
        context.put(index, sb.toString());
        return true;
    }
    private static int nextElement(int key, List<String> partList) {
        return Integer.parseInt(partList.remove(0)) ^ key;
    }
}
