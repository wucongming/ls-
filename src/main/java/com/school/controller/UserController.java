package com.school.controller;

import com.alibaba.fastjson.JSONArray;
import com.school.entity.MsgEntity;
import com.school.entity.UserEntity;
import com.school.service.MsgService;
import com.school.service.UserService;
import com.school.util.IMProtocol;
import com.school.vo.Message;
import com.school.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.school.util.IMType.REFRESH_FRIEND_LIST;
import static com.school.vo.IMStatus.ADD_FRIEND;


@RestController
@RequestMapping(
        value="/user",
        produces = "application/json;charset=utf-8")
public class UserController {



    @Autowired
    private UserService userService;
    @Autowired
    private MsgService msgService;

    @RequestMapping(
            value="/register",
            method = RequestMethod.PUT)
    public Message<Object> register(@RequestBody UserEntity user) {
        boolean isExist = userService.findUserByAccount(user.getAccount());
        if(isExist) return new Message<Object>("账号已存在",-1,null);;
        UserEntity register = userService.register(user);
        if(register == null) return new Message<Object>("注册失败，请稍后再试",0,null);
        return new Message<Object>("注册成功",1,null);
    }


    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    public Message<UserEntity> login(@RequestBody UserEntity user, HttpServletRequest request) {
        user = userService.findUserByAccountAndPasswd(user.getAccount(), user.getPasswd());
        if(user == null) return new Message<UserEntity>("登录失败",0,null);
        request.getSession().setAttribute("user",user);
        return new Message<UserEntity>("登录成功", 1, user);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public Message<UserVO> updateUserInfo(@RequestBody UserEntity user) {
//        if(false) return new Message<UserVO>();
        UserVO userVO = null;
        try {
            userVO = userService.updateUser(user);
        } catch(Exception e) {
            return new Message<UserVO>("修改失败", -1, null);
        }
        return new Message<UserVO>("修改成功", 1, userVO);
    }


    @RequestMapping(
            value = "/update/headImg",
            method = RequestMethod.POST
    )
    public Message<UserVO> updateheadImg(@RequestBody  Map<String,String> map, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        String headImg = map.get("headImg");
        if(user == null || headImg == null) return new Message<>("请登录", -1, null);

        user.setHeadImg(headImg);
        UserVO vo = null;
        try {
            vo = userService.updateUser(user);
        } catch (Exception e) {
            return new Message<>("修改失败", -1, null);
        }
        return new Message<>("修改成功",1 ,vo);
    }



    @RequestMapping(
            value = "/search/{name}",
            method = RequestMethod.GET
    )
    public Message<List<UserVO>> searchFriend(@PathVariable("name") String name) {
        List<UserVO> userVOs = userService.searchUserByName(name);
        if(userVOs.size() == 0) return new Message<>("查找成功", 0, null);
        return new Message<>("查找成功", 1, userVOs);
    }

    @RequestMapping(
            value = "/add/friend/{id}",
            method = RequestMethod.POST
    )
    public Message<UserEntity> addFriend(@PathVariable("id") int fid, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if(user == null) return new Message<>("添加好友失败，请重新登录", -1, null);
//        if(fid.equals("-1")) return new Message<>("好友不存在")
        if(fid == user.getUid()) return new Message<>("不允许添加自己为好友", -1, null);
        String msg = IMProtocol.produceAddFriend(user.getUid(),fid);


        boolean contains = IMController.livingSessions.containsKey(fid);

        if(!contains) {
//            redisMsgService.addFriend(fid, msg);
            MsgEntity entity = new MsgEntity();
            entity.setFromid(user.getUid());
            entity.setFromname(user.getName());
            entity.setToid(fid);
            entity.setType(ADD_FRIEND.ordinal());
            msgService.saveMsg(entity);
            return new Message<>("用户未上线", -1, null);
        } else {
            IMController.sendMsg(String.valueOf(fid), msg);
        }
        return new Message<>("发送成功", 1, null);
    }

    @RequestMapping(
            value = "/argue/{mid}",
            method = RequestMethod.PUT
    )
    public Message<Object> argueAddFriend(@PathVariable("mid")int mid, HttpServletRequest request) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        if(userEntity == null) return new Message<>("对不起，请登录", -1, null);
        String friends = userEntity.getFriends();

        int i = userService.addFriend(mid);
        if(i == -1) return new Message<>("对方已是您的好友", -1, null);
        else if(i == 0) return new Message<>("系统错误", -1, null);
        return new Message<>("添加成功", 1, null);
    }

    @RequestMapping(
            value = "/ignore/{mid}",
            method = RequestMethod.PUT
    )
    public Message<Object> ignoreAddFriend(@PathVariable("mid")int mid, HttpServletRequest request) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        if(userEntity == null) return new Message<>("对不起，请登录", -1, null);
        userService.ignoreFriend(mid);

        return new Message<>("忽略成功", 1, null);
    }

    @RequestMapping(
            value = "/friend/all",
            method = RequestMethod.GET
    )
    public Message<List<UserVO>> refreshFriendList(HttpServletRequest request) {
        UserEntity entity = (UserEntity) request.getSession().getAttribute("user");
        if(entity == null) return new Message<>("请登录", -1, null);
        List<UserVO> list = userService.loadAllFriend(entity.getUid());
        return new Message<>("加载成功", 1, list);
    }



    @RequestMapping(
            value = "/detail/{id}",
            method = RequestMethod.GET
    )
    public Message<UserVO> refreshFriendList(@PathVariable("id")int id) {
        UserVO user = userService.getUser(id);
        return new Message<>("加载成功", 1, user);
    }


    @RequestMapping(
            value = "/del/friend/{uid}",
            method = RequestMethod.DELETE
    )
    public Message<UserVO> refreshFriendList(@PathVariable("uid")int uid, HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if(user == null) return new Message<>("请登录", -1, null);
        userService.delFriend(user.getUid(),uid);
        return new Message<>("加载成功", 1, null);
    }

}
