package com.school.controller;

import com.school.entity.CourseEntity;
import com.school.entity.StudentCourseEntity;
import com.school.entity.UserEntity;
import com.school.service.CourseService;
import com.school.service.StudentCourseService;
import com.school.vo.CourseNameVO;
import com.school.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    private ConcurrentHashMap<Integer,String> keys = new ConcurrentHashMap<>();

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Message<Object> save(@RequestBody CourseEntity course, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        course.setUserByTid(user);
        boolean save = courseService.save(course);
        if(save) return new Message<Object>("保存成功", 1, null);
        else return new Message<>("保存失败,请检查后重试", 0, null);
    }

    @RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public Message<Object> search(@PathVariable("keyword") String keyWord,
                                  @RequestParam(value = "type",required = false, defaultValue = "0") Integer type) {
        List<CourseNameVO> vos = null;
        if(keyWord.equals("default")) {
            vos  = courseService.getTopNumber(0);
        } else if(type == 0) {
            vos = courseService.getByCnameLike(keyWord);
        } else {
            vos = courseService.getByTags(keyWord);
        }

        return new Message<>("查询成功", 1, vos);
    }

    @RequestMapping(
            value = "/search/detail/{keyword}",
            method = RequestMethod.GET
    )
    public Message<Object> searchDetail(@PathVariable("keyword") String keyWord) {
        List<CourseEntity> courses = courseService.getDetailByKeyWord(keyWord);
        return new Message<>("查询成功", 1, courses);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Message<Object> detail(@PathVariable("id") int id) {
        CourseEntity course = null;
        try {
            course = courseService.findByCid(id);
        } catch (Exception e) {
            return new Message<>("查询失败", -1 ,null);
        }

        if(course == null) return new Message<>("课程不存在", 0, null);
        Map<String,Object> map = new HashMap<>();
        map.put("course",course);
        course.getUserByTid().setPasswd("");
        map.put("user", course.getUserByTid());
        return new Message<>("查询成功", 1, map);
    }


    @RequestMapping(
            value = "/my/create/{nowPage}",
            method = RequestMethod.GET
    )
    public Page<CourseEntity> getMyCreateCourse(HttpServletRequest request, @PathVariable("nowPage") int nowPage) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
//        if(user == null) return new Message("请登录", -1, null);
        if(user.getRole() == 0)
            return  courseService.findByUidPage(user.getUid(), nowPage, 3);
        else
            return courseService.findByUidFromSC(user.getUid(), nowPage, 3);
    }

    @RequestMapping(
            value = "/del/{cid}",
            method = RequestMethod.GET
    )
    public Message<Object> delCourse(HttpServletRequest request,@PathVariable("cid")int cid) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        try {
            courseService.delMyCourse(user.getUid(), cid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message<>("失败", -1, null);
        }
        return new Message<Object>("成功", 1, null);
    }

    @RequestMapping(
            value = "/verify/{cid}",
            method = RequestMethod.GET
    )
    public Message verifyCourseAuthority(@PathVariable("cid")int cid, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if(user == null) return new Message("请登录", -1, null);
        String uuid = null;
        if(keys.containsKey(cid)) uuid = keys.get(cid);
        else {
            uuid = UUID.randomUUID().toString();
            keys.put(cid,uuid);
        }
        IMController.addClazz(cid, user.getUid(), user.getName());
        boolean result = courseService.verifyUser(user.getUid(), cid);
        if(result) return new Message("成功", 1, uuid);
        else return new Message("成功", 0, uuid);
    }

    @RequestMapping(
            value = "/verify/add/{cid}",
            method = RequestMethod.GET
    )
    public Message verifyHasAddCourse(@PathVariable("cid")int cid, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if(user == null) return new Message("请登录", 0, null);
        boolean result = courseService.verifyHasAddCourse(user.getUid(), cid);
        if(result) return new Message("ok", 1, null);
        else return new Message("请加入课程", -1, null);
    }


    @RequestMapping(
            value = "/add/{cid}",
            method = RequestMethod.PUT
    )
    public Message addInCourse(@PathVariable("cid")int cid, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if(user == null) return new Message("请登录", 0, null);
        boolean result = studentCourseService.joinCourse(user.getUid(), cid);
        if(result) return  new Message("加入成功", 1, null);
        else return  new Message("加入失败", 0, null);
    }
}
