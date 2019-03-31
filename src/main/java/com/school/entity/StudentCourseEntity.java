package com.school.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_course", schema = "school", catalog = "")
public class StudentCourseEntity {
    private int scid;
    private String schedule;
    private UserEntity userByUid;
    private CourseEntity courseByCid;

    @Id
    @Column(name = "scid")
    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    @Basic
    @Column(name = "schedule")
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseEntity that = (StudentCourseEntity) o;
        return scid == that.scid &&
                Objects.equals(schedule, that.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scid, schedule);
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    public UserEntity getUserByUid() {
        return userByUid;
    }

    public void setUserByUid(UserEntity userByUid) {
        this.userByUid = userByUid;
    }

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "cid", nullable = false)
    public CourseEntity getCourseByCid() {
        return courseByCid;
    }

    public void setCourseByCid(CourseEntity courseByCid) {
        this.courseByCid = courseByCid;
    }
}
