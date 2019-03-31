package com.school.repository;

import com.school.entity.CourseEntity;
import com.school.entity.StudentCourseEntity;
import com.school.entity.UserEntity;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, Integer> {

    @Query("select a from StudentCourseEntity a " +
            "where a.userByUid.uid = ?1 and a.courseByCid.cid = ?2")
    public StudentCourseEntity findByUserByUidAndCourseByCid(int uid,int cid);

    @Query("select a.courseByCid from StudentCourseEntity  a " +
            "where a.userByUid.uid = ?1 ")
    public List<CourseEntity> findByUserByUid(int uid);


    public Page<StudentCourseEntity> findAll(Specification<StudentCourseEntity> spec, Pageable pageAble);


//    @SQLInsert("insert Stu")
//    public void insert(int uid,int cid);
}
