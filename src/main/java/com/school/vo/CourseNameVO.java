package com.school.vo;

public class CourseNameVO  {


    private int cid;

    private String value;

    private String img;

    private String intro;



    public CourseNameVO(int cid, String value) {
        this.cid = cid;
        this.value = value;
    }

    public CourseNameVO(int cid, String value, String img, String intro) {
        this.cid = cid;
        this.value = value;
        this.img = img;
        this.intro = intro;
    }

    public CourseNameVO() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CourseNameVO{" +
                "cid=" + cid +
                ", value='" + value + '\'' +
                '}';
    }
}
