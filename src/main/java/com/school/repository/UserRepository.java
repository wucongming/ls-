package com.school.repository;

import com.school.entity.UserEntity;
import com.school.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query("select a from UserEntity a where a.account = ?1")
    public UserEntity findByAccount(String account);

    @Query("select a from UserEntity a where a.account = ?1 and a.passwd = ?2")
    public UserEntity findByAccountAndPasswd(String account, String passwd);

    @Query("select new com.school.vo.UserVO(a.uid, a.name, a.headImg, a.account, a.account, a.role, a.addr, a.intro, a.sex) " +
            "from UserEntity a " +
            "where a.name like ?1")
    public List<UserVO> findByNameLike(String name);

    public UserEntity findByUid(int uid);

    @Query("select new com.school.vo.UserVO(a.uid, a.name, a.headImg, a.account, a.account, a.role, a.addr, a.intro, a.sex) " +
            "from UserEntity a " +
            "where a.uid in ?1")
    public List<UserVO> findByUidIn(List<Integer> list);

    @Query("select new com.school.vo.UserVO(a.uid, a.name, a.headImg, a.account, a.account, a.role, a.addr, a.intro, a.sex) " +
            "from UserEntity a " +
            "where a.uid = ?1")
    public UserVO findUserVOByUid(int uid);


}
