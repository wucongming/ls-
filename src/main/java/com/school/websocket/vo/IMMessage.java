package com.school.websocket.vo;

import com.school.vo.UserVO;

public class IMMessage {

    /**
     *
     * "{" +
     *                 "'id': -1," +
     *                 "'fromid': '" + uid + "'," +
     *                 "'fromname': '" + name + "'," +
     *                 "'toid': -1," +
     *                 "'toname': null," +
     *                 "'type': " + SEND_CLAZZS.ordinal() + "," +
     *                 "'message': '" + name + "同学进入了直播课程中'" +
     *                 "}";
     */
    private int id;
    private int fromid;
    private String fromname;
    private int toid;
    private String toname;
    private int type;
    private String message;

    public IMMessage() {
    }

    public IMMessage(int id, int fromid, String fromname, int toid, String toname, int type, String message) {
        this.id = id;
        this.fromid = fromid;
        this.fromname = fromname;
        this.toid = toid;
        this.toname = toname;
        this.type = type;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


