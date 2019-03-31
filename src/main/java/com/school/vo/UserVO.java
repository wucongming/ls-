package com.school.vo;


public class UserVO {

    private int uid;
    private String name;
    private String headImg;
    private String account;
    private String passwd;
    private int role;
    private String addr;
    private String intro;
    private Integer sex;

    public UserVO() {
    }

    public UserVO(int uid, String name, String headImg, String account, String passwd, int role, String addr, String intro, Integer sex) {
        this.uid = uid;
        this.name = name;
        this.headImg = headImg;
        this.account = account;
        this.passwd = passwd;
        this.role = role;
        this.addr = addr;
        this.intro = intro;
        this.sex = sex;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
