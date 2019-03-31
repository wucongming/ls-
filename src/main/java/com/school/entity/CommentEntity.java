package com.school.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "school", catalog = "")
public class CommentEntity {
    private int cid;
    private int type;
    private String message;
    private String time;
    private UserEntity userByCuid;
    private UserEntity userByCrid;

    @Id
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return cid == that.cid &&
                type == that.type &&
                Objects.equals(message, that.message) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, type, message, time);
    }

    @ManyToOne
    @JoinColumn(name = "cuid", referencedColumnName = "uid", nullable = false)
    public UserEntity getUserByCuid() {
        return userByCuid;
    }

    public void setUserByCuid(UserEntity userByCuid) {
        this.userByCuid = userByCuid;
    }

    @ManyToOne
    @JoinColumn(name = "crid", referencedColumnName = "uid", nullable = false)
    public UserEntity getUserByCrid() {
        return userByCrid;
    }

    public void setUserByCrid(UserEntity userByCrid) {
        this.userByCrid = userByCrid;
    }
}
