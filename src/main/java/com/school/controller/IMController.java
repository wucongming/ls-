package com.school.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.school.entity.MsgEntity;
import com.school.repository.MsgRepository;
import com.school.util.IMType;
import com.school.vo.IMStatus;
import com.school.websocket.vo.IMMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import static com.school.util.IMType.LOGIN;
import static com.school.util.IMType.SEND_CLAZZS;
import static com.school.util.IMType.SEND_ORTHER;

@Controller
@ServerEndpoint("/ws/school/{uid}")
public class IMController {



    public static Map<String, Session> livingSessions = new ConcurrentHashMap<>();
    public static Map<Integer, Vector<String>> clazzs = new ConcurrentHashMap<Integer, Vector<String>>();

    private static MsgRepository msgRepository;

    @Autowired
    public void setMsgRepository(MsgRepository msgRepository) {
        IMController.msgRepository = msgRepository;
    }

    public static void sendMsg(String fid, String msg) {
        Session session = livingSessions.get(fid);
        session.getAsyncRemote().sendText(msg);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        System.out.println("登录成功" + uid);
//        String
        livingSessions.put(uid,session);
//        IMMessage imMessage = new IMMessage();

//        imMessage.setCode(LOGIN.ordinal());
//        imMessage.setMessage("登陆成功");

//        session.getAsyncRemote().sendText(JSONObject.toJSONString(imMessage));

    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userName") String userName) {
        System.out.println("接收消息   [" + message + "]   ");
//        = (MsgEntity)JSON.parse(message);
        MsgEntity entity = JSON.parseObject(message,MsgEntity.class);
//        System.out.println();
        switch (IMType.values()[entity.getType()]) {
            case SEND_ORTHER: sendOther(entity, message); break;
            case SEND_CLAZZS: broadCastClazz(entity.getToid(),message);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    @OnClose
    public void onClose(Session session, @PathParam("userName") String userName) {
        livingSessions.remove(session.getId());
    }

    public static void notifyUser(int uid,int type, String msg) {
        Session session = livingSessions.get(uid);
        if(session != null) {
            MsgEntity entity = new MsgEntity();
            entity.setType(type);
            entity.setToid(uid);
            entity.setMessage(msg);
            session.getAsyncRemote().sendText(JSON.toJSONString(entity));
        }
    }

    private void sendOther(MsgEntity entity, String msg) {
        Session session = livingSessions.get(String.valueOf(entity.getToid()));
        System.out.println(session + " , " + livingSessions.size());
        if(session == null) {
            msgRepository.save(entity);
        } else {
            session.getAsyncRemote().sendText(msg);
        }
    }

    public static synchronized void addClazz(int cid,int uid,String name) {
        Vector<String> vector = null;
        if(clazzs.containsKey(cid)) {
            vector = clazzs.get(cid);
        } else {
            vector = new Vector<>();
            clazzs.put(cid, vector);
        }
        vector.add(String.valueOf(uid));
//        String msg = "{" +
//                "'id': -1," +
//                "'fromid': '" + uid + "'," +
//                "'fromname': '" + name + "'," +
//                "'toid': -1," +
//                "'toname': null," +
//                "'type': " + SEND_CLAZZS.ordinal() + "," +
//                "'message': '" + name + "同学进入了直播课程中'" +
//                "}";
        IMMessage imMessage = new IMMessage(-1, uid, name, -1,
                "系统", SEND_CLAZZS.ordinal(), name + "同学进入了课程");
        broadCastClazz(cid,JSONObject.toJSONString(imMessage));
    }

    private static void broadCastClazz(int cid,String msg) {
        System.out.println("发送广播" );
        Vector<String> uids = clazzs.get(cid);
        for(String uid : uids) {
            Session session = livingSessions.get(uid);
            System.out.println(session);
            if(session.isOpen()) {
                synchronized (session) {
//                    System.out.println("发送" );
                    session.getAsyncRemote().sendText(msg);
                }
            } else {
                livingSessions.remove(uid);
            }

        }
    }
}
