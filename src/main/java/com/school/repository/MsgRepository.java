package com.school.repository;

import com.school.entity.MsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgRepository extends JpaRepository<MsgEntity, Integer> {

    @Query("select m from MsgEntity m where m.toid = ?1")
    public List<MsgEntity> getAllByToid(int uid);

    @Query("select m from MsgEntity m where m.fromid = ?1 and m.toid = ?2 and m.type = ?3")
    public List<MsgEntity> getAllByFromidAndToidAndType(int fromId,int toId,int type);
}
