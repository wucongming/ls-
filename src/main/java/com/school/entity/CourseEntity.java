package com.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "school", catalog = "")
public class CourseEntity {
    private int cid;
    private String cname;
    private String tags;
    private String img;
    private int type;
    private String content;
    private String intro;
    private UserEntity userByTid;
    private Collection<StudentCourseEntity> studentCoursesByCid;

    @Id
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Basic
    @Column(name = "tags")
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Basic
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
    @Column(name = "content")
    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    @Basic
    @Column(name = "intro")
    public String getIntro() { return intro; }

    public void setIntro(String intro) { this.intro = intro; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return cid == that.cid &&
                type == that.type &&
                Objects.equals(cname, that.cname) &&
                Objects.equals(tags, that.tags) &&
                Objects.equals(img, that.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, cname, tags, img, type);
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tid", referencedColumnName = "uid", nullable = false)
    public UserEntity getUserByTid() {
        return userByTid;
    }

    public void setUserByTid(UserEntity userByTid) {
        this.userByTid = userByTid;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "courseByCid")
    public Collection<StudentCourseEntity> getStudentCoursesByCid() {
        return studentCoursesByCid;
    }

    public void setStudentCoursesByCid(Collection<StudentCourseEntity> studentCoursesByCid) {
        this.studentCoursesByCid = studentCoursesByCid;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", tags='" + tags + '\'' +
                ", img='" + img + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", userByTid=" + userByTid +
                ", studentCoursesByCid=" + studentCoursesByCid +
                '}';
    }
}
