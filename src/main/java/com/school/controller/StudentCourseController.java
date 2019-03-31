package com.school.controller;

import com.school.entity.UserEntity;
import com.school.service.StudentCourseService;
import com.school.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sc")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @RequestMapping(
            value = "/join/{cid}",
            method = RequestMethod.PUT
    )
    public Message<Object> joinCourse(@PathVariable("cid") int cid, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if(user == null) return new Message<>("加入失败，请重新登录", -2, null);
        studentCourseService.joinCourse(user.getUid(),cid);
        return new Message<>("加入成功", 1, null);
    }

}
