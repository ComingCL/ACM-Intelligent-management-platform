package com.dhu.utils;

import com.dhu.config.Result;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/22 13:17
 */
public class FileUtil {
    /**
     * 下载文件
     */
    public static void downloadFiles(HttpServletRequest request, HttpServletResponse response,
                                String filename){
//        if(!StringUtils.hasLength(filename)){
//            return Result.error("文件名为空");
//        }
        ClassPathResource classPathResource = new ClassPathResource("templates/" + filename);
        File file = null;
        try{
            file = classPathResource.getFile();
        }catch (IOException e){
            e.printStackTrace();
            return;
//            return Result.error("文件名不存在");
        }
//        让服务器告诉浏览器它发送的数据属于什么文件类型
        response.setHeader("content-type", "application/octet-stream");
//        设置强制下载不打开
        response.setContentType("application/force-download");
//        设置文件名
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        byte[] buffer = new byte[1024];
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try{
            inputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(inputStream);
            OutputStream outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while(i != -1){
                outputStream.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedInputStream != null){
                try{
                    bufferedInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
//        return Result.success("文件下载成功");
    }
}
