package com.majingji.service;

import com.majingji.properties.UploadsProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadsService {
    private UploadsProperties uploadsProperties;
    public UploadsService(UploadsProperties uploadsProperties){
        this.uploadsProperties = uploadsProperties;
    }
    //文件批量上传的方法
    public String uploads(MultipartFile[] uploadFiles, HttpServletRequest request){
        List list = new ArrayList();//存储生成的访问路径
        //判断是否上传了文件
        if (uploadFiles.length > 0) {
            //遍历集合中
            for (int i = 0; i < uploadFiles.length; i++) {
                //根据下标获取MultipartFile对象
                MultipartFile uploadFile = uploadFiles[i];
                //设置上传文件的位置在该项目目录下的uploadFile文件夹下，并根据上传的文件日期，进行分类保存
//                String realPath = request.getSession().getServletContext().getRealPath("uploadFile");
//                String format = sdf.format(new Date());
//                File folder = new File(realPath + format);
//                if (!folder.isDirectory()) {
//                    folder.mkdirs();
//                }
                //获取文件的原始名称
                String oldName = uploadFile.getOriginalFilename();
                System.out.println("oldName = " + oldName);
                //生成新的文件名称
                String newName = UUID.randomUUID().toString() + oldName.
                        substring(oldName.lastIndexOf("."), oldName.length());
                System.out.println("newName = " + newName);
                try {
                    //保存文件,保存位置是在G盘下的pic文件夹
                    uploadFile.transferTo(new File("G:\\pic", newName));

                    //生成上传文件的访问路径
                    /**
                     * request.getScheme()返回当前页面使用的协议,http或者https
                     * request.getServerName()返回当前页面所在的服务器的名字
                     * request.getServerPort()可以返回当前页面所在的服务器使用的端口
                     * request.getContextPath()可以返回当前页面所在的应用的名字
                     */
                    String filePath = request.getScheme() + "://" + request.getServerName() + ":"+ request.getServerPort() + "/img/"+ newName;
                    //将生成的访问路径存储到list集合中
                    list.add(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return list.toString();
        } else if (uploadFiles.length == 0) {
            return "请选择文件";
        }
        return "上传失败";
    }
}
