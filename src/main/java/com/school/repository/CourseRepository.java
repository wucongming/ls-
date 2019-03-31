package com.school.repository;

import com.school.entity.CourseEntity;
import com.school.vo.CourseNameVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {

//    public CourseNameVO findByCid(int cid);


    @Query(value = "select new com.school.vo.CourseNameVO(a.cid,a.cname, a.img, a.intro) " +
            "from CourseEntity a ")
    public Page<CourseNameVO> findTop30(Pageable pageable);

    @Query(value = "select new com.school.vo.CourseNameVO(a.cid, a.cname, a.img, a.intro) " +
            "from CourseEntity a " +
            "where a.cname like ?1")
    public List<CourseNameVO> findByCnameLike(String cname);

    @Query(value = "select a " +
            "from CourseEntity a " +
            "where a.cname like ?1")
    public List<CourseEntity> findDetailByCnameLike(String cname);


    @Query(value = "select new com.school.vo.CourseNameVO(a.cid, a.cname, a.img, a.intro) " +
            "from CourseEntity a " +
            "where a.tags like ?1")
    public List<CourseNameVO> findByTagsLike(String tag);

    public CourseEntity findByCid(int cid);


    @Query(value = "select a " +
            "from CourseEntity a " +
            "where a.userByTid.uid = ?1")
    public List<CourseEntity> findByUserByTid(int uid);

    Page<CourseEntity> findAll(Specification<CourseEntity> spec, Pageable pageAble);
}

