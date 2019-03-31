package com.school.service;

import com.school.entity.MsgEntity;
import com.school.repository.MsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.school.util.IMType.LOAD_ONE_OFFLINE_MSG;

@Service
public class MsgService {

    @Autowired
    private MsgRepository msgRepository;

    public List<MsgEntity> getOffLineMsg(int uid) {
        List<MsgEntity> msgs = msgRepository.getAllByToid(uid);
        return msgs;
    }

    public void saveMsg(MsgEntity msg) {
        msgRepository.save(msg);
    }


    public List<MsgEntity> readMsg(int fromId,int toId) {
        List<MsgEntity> list = msgRepository.getAllByFromidAndToidAndType(fromId, toId, LOAD_ONE_OFFLINE_MSG.ordinal());
        msgRepository.deleteAll(list);
        return list;
    }

}
