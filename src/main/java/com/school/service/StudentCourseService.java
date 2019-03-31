package com.school.service;

import com.school.entity.StudentCourseEntity;
import com.school.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public boolean joinCourse(int uid,int cid) {
        try {
            StudentCourseEntity sc = studentCourseRepository.findByUserByUidAndCourseByCid(uid, cid);
            if(sc != null) return false;

        } catch (Exception e) {
            return false;
        }


        return false;
    }
}
