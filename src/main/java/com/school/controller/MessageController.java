package com.school.controller;


import com.school.entity.MsgEntity;
import com.school.entity.UserEntity;
import com.school.service.MsgService;
import com.school.vo.Message;
import com.school.websocket.vo.IMMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(
        value="/message",
        produces = "application/json;charset=utf-8")
public class MessageController {

    @Autowired
    private MsgService msgService;


    @RequestMapping(
            value = "/off/{uid}",
            method = RequestMethod.GET
    )
    public Message<List<MsgEntity>> getOffLineMsg(@PathVariable("uid") int uid) {
        List<MsgEntity> offLineMsg = msgService.getOffLineMsg(uid);
        return new Message<>("查询成功", 1, offLineMsg);
    }

    @RequestMapping(
            value = "/read/{fid}",
            method = RequestMethod.GET
    )
    public Message<List<MsgEntity>> readMsg(@PathVariable("fid")int fromId, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if(user == null) return new Message<>("请登录", -1 ,null);
        List<MsgEntity> list = msgService.readMsg(fromId, user.getUid());
        return new Message<>("加载成功", 1, list);
    }

}
