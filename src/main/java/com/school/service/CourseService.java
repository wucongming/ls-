package com.school.service;

import com.school.entity.CourseEntity;
import com.school.entity.StudentCourseEntity;
import com.school.entity.UserEntity;
import com.school.repository.CourseRepository;
import com.school.repository.StudentCourseRepository;
import com.school.repository.UserRepository;
import com.school.vo.CourseNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public boolean save(CourseEntity courseEntity) {

        try {
            CourseEntity save = courseRepository.save(courseEntity);
            if(save == null) return false;
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public List<CourseNameVO> getTopNumber(int number) {
//        return courseRepository.findTop30ByCidAfter(0);
        Pageable pageable = new PageRequest(0, 30, Sort.Direction.ASC, "cid");
        return courseRepository.findTop30(pageable).getContent();
    }

    public List<CourseNameVO> getByCnameLike(String cname) {
        return courseRepository.findByCnameLike("%" + cname + "%");
    }

    public CourseEntity findByCid(int cid) {
        return courseRepository.findByCid(cid);
    }

    public List<CourseEntity> getDetailByKeyWord(String keyWord) {
        return courseRepository.findDetailByCnameLike("%" + keyWord + "%");
    }

    public List<CourseNameVO> getByTags(String tag) {
        return courseRepository.findByTagsLike("%" + tag + "%");
    }

    public List<CourseEntity> findMyCourse(int uid) {
        return courseRepository.findByUserByTid(uid);
    }

    public Page<CourseEntity> findByUidFromSC(int uid,int nowPage,int pageSize) {
        UserEntity user = userRepository.findByUid(uid);
        Pageable pageAble = new PageRequest(nowPage - 1,pageSize);
        Specification<StudentCourseEntity> spec = new Specification<StudentCourseEntity>() {
            @Override
            public Predicate toPredicate(Root<StudentCourseEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> prs = new ArrayList<>();
//                criteriaBuilder.
                prs.add(criteriaBuilder.equal(
                        root.get("userByUid").as(UserEntity.class),
                        user
                ));
                return criteriaBuilder.and(prs.toArray(new Predicate[prs.size()]));
            }
        };
        Page<StudentCourseEntity> page = studentCourseRepository.findAll(spec, pageAble);
        List<StudentCourseEntity> list = page.getContent();
        List<CourseEntity> datas = new ArrayList<>();

        for(StudentCourseEntity item : list) {
            datas.add(item.getCourseByCid());
        }
        Page<CourseEntity> newPage = new PageImpl<>(datas, pageAble, datas.size());
        return newPage;
    }

    public Page<CourseEntity> findByUidPage(int uid,int nowPage,int pageSize) {
        UserEntity user = userRepository.findByUid(uid);
        Pageable pageAble = new PageRequest(nowPage - 1,pageSize,new Sort(Sort.Direction.DESC,"cid"));
        Specification<CourseEntity> spec = new Specification<CourseEntity>() {
            @Override
            public Predicate toPredicate(Root<CourseEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> prs = new ArrayList<>();
//                criteriaBuilder.
                prs.add(criteriaBuilder.equal(
                        root.get("userByTid").as(UserEntity.class),
                        user
                ));
                return criteriaBuilder.and(prs.toArray(new Predicate[prs.size()]));
            }
        };
        return courseRepository.findAll(spec,pageAble);
    }


    public void delMyCourse(int uid,int cid) throws Exception {
        CourseEntity course = courseRepository.findByCid(cid);
        if(course.getUserByTid().getUid() != uid) throw new Exception();
        courseRepository.delete(course);
    }

    public boolean verifyUser(int uid,int cid) {
        CourseEntity course  = courseRepository.findByCid(cid);
        if(course.getUserByTid().getUid() == uid) return true;
        else return false;
    }

    public boolean verifyHasAddCourse(int uid,int cid) {
        StudentCourseEntity result = studentCourseRepository.findByUserByUidAndCourseByCid(uid, cid);
        CourseEntity course = courseRepository.findByCid(cid);

        return result != null || course.getUserByTid().getUid() == uid ? true : false;
    }
}
