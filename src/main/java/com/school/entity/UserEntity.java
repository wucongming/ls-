package com.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "school", catalog = "")
public class UserEntity {
    private int uid;
    private String name;
    private String headImg = "default.jpg";
    private String account;
    private String passwd;
    private int role;
    private String addr;
    private String intro = "";
    private Integer sex;
    private String friends;
    private Collection<CommentEntity> commentsByUid;
    private Collection<CommentEntity> commentsByUid_0;
    private Collection<CourseEntity> coursesByUid;
    private Collection<EassyEntity> eassiesByUid;
    private Collection<StudentCourseEntity> studentCoursesByUid;

    @Id
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "head_img")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Basic
    @Column(name = "addr")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Basic
    @Column(name = "intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Basic
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "friends")
    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return uid == that.uid &&
                role == that.role &&
                Objects.equals(name, that.name) &&
                Objects.equals(headImg, that.headImg) &&
                Objects.equals(account, that.account) &&
                Objects.equals(passwd, that.passwd) &&
                Objects.equals(addr, that.addr) &&
                Objects.equals(intro, that.intro) &&
                Objects.equals(sex, that.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, name, headImg, account, passwd, role, addr, intro, sex);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByCuid")
    public Collection<CommentEntity> getCommentsByUid() {
        return commentsByUid;
    }

    public void setCommentsByUid(Collection<CommentEntity> commentsByUid) {
        this.commentsByUid = commentsByUid;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByCrid")
    public Collection<CommentEntity> getCommentsByUid_0() {
        return commentsByUid_0;
    }

    public void setCommentsByUid_0(Collection<CommentEntity> commentsByUid_0) {
        this.commentsByUid_0 = commentsByUid_0;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByTid")
    public Collection<CourseEntity> getCoursesByUid() {
        return coursesByUid;
    }

    public void setCoursesByUid(Collection<CourseEntity> coursesByUid) {
        this.coursesByUid = coursesByUid;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByUid")
    public Collection<EassyEntity> getEassiesByUid() {
        return eassiesByUid;
    }

    public void setEassiesByUid(Collection<EassyEntity> eassiesByUid) {
        this.eassiesByUid = eassiesByUid;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByUid")
    public Collection<StudentCourseEntity> getStudentCoursesByUid() {
        return studentCoursesByUid;
    }

    public void setStudentCoursesByUid(Collection<StudentCourseEntity> studentCoursesByUid) {
        this.studentCoursesByUid = studentCoursesByUid;
    }
}
