package com.school.controller;


import com.school.entity.UserEntity;
import com.school.vo.Message;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @Value("${file.head-path}")
    private String headPathName;

    @Value("${file.course-path}")
    private String coursePathName;

    @RequestMapping(value="/uploadfile",method= RequestMethod.POST)
    public Message uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if(user == null) return new Message("对不起，请登录", -1, null);
        String fileName = user.getAccount() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
        File toFile = new File(headPathName + fileName);
        return upload(file, toFile, fileName);

    }

    @RequestMapping(value = "/upload/course/image",method = RequestMethod.POST)
    public Message uploadCourseImg(@RequestParam("file") MultipartFile file) {

        String fileName = UUID.randomUUID() +
                file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
//        System.out.println("上传课程照片" + coursePathName +  fileName);
        File toFile = new File(coursePathName + fileName);
        return upload(file,toFile,fileName);
    }

    private Message<Object> upload(MultipartFile file,File toFile,String fileName) {
        try {
            toFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!toFile.exists()) return new Message("文件创建失败", -1, null);
        try {
            FileCopyUtils.copy(file.getBytes(), toFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new Message("上传失败", -1, null);
        }
        System.out.println("文件名" + "   " + file.getName());
        return new Message("上传成功", 1, fileName);
    }




}
