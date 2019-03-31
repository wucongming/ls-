package com.school.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.school.controller.IMController;
import com.school.entity.MsgEntity;
import com.school.entity.UserEntity;
import com.school.repository.MsgRepository;
import com.school.repository.UserRepository;
import com.school.vo.UserVO;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.school.util.IMType.REFRESH_FRIEND_LIST;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MsgRepository msgRepository;

    public UserEntity register(UserEntity user) {
        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public boolean findUserByAccount(String account) {
        return userRepository.findByAccount(account) == null ? false : true;
    }

    public UserEntity findUserByAccountAndPasswd(String account,String passwd) {
        return userRepository.findByAccountAndPasswd(account,passwd);
    }

    public UserVO updateUser(UserEntity user) {
        user = userRepository.save(user);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    public List<UserVO> searchUserByName(String name) {
        return userRepository.findByNameLike("%" + name + "%");
    }

    public int addFriend(int mid) {
        MsgEntity entity = msgRepository.findById(mid).get();
        if(entity == null) return 0;
        int uid = entity.getFromid();
        int fid = entity.getToid();
        msgRepository.delete(entity);
        int x = updateUserFriend(uid,fid);
        int y = updateUserFriend(fid,uid);
        if(x == 0 || y == 0) return -1;
        IMController.notifyUser(uid,REFRESH_FRIEND_LIST.ordinal(), entity.getToname() + "同意添加请求");
        return 1;
    }

    private int updateUserFriend(int uid,int fid) {
        UserEntity userEntity = userRepository.findByUid(uid);
        String friends = userEntity.getFriends();
        List<Integer> list;
        System.out.println("用户当前的好友列表===========》" + friends);
        if(friends == null || friends.trim().equals("")) {
            list = new ArrayList<>();
        } else {
            list = JSONArray.parseArray(friends, Integer.class);
            if(list.contains(fid)) return 0;
        }

        list.add(fid);
        friends = JSONArray.toJSONString(list);
        System.out.println("添加好友=======>" + friends);
        userEntity.setFriends(friends);
        userRepository.save(userEntity);
        return 1;
    }

    public List<UserVO> loadAllFriend(int uid) {
        UserEntity entity = userRepository.findByUid(uid);
        String friends = entity.getFriends();
        List<Integer> list = (ArrayList<Integer>) JSONArray.parseArray(friends,Integer.class);
        return userRepository.findByUidIn(list);
    }

    public void ignoreFriend(int mid) {
        MsgEntity entity = msgRepository.findById(mid).get();
        msgRepository.delete(entity);
    }

    public UserVO getUser(int uid) {
        return userRepository.findUserVOByUid(uid);
    }

    public void delFriend(int uid,int fid) {
        UserEntity me = userRepository.findByUid(uid);
        UserEntity you = userRepository.findByUid(fid);
        deleteFriend(me,you);
        deleteFriend(you,me);
    }

    private void deleteFriend(UserEntity me,UserEntity you) {
        String friends = me.getFriends();
        List<Integer> list = JSONArray.parseArray(friends,Integer.class);
        list.remove(list.indexOf(you.getUid()));
        me.setFriends(JSONArray.toJSONString(list));
        userRepository.save(me);
    }
}
