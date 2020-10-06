package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Student {
    private Integer stuId;
    private String stuSn;
    private Integer stuCid;
    private String stuPw;
    private String photo;
    private String sex;
    private String remark;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getStuCid() {
        return stuCid;
    }

    public void setStuCid(Integer stuCid) {
        this.stuCid = stuCid;
    }

    public String getStuPw() {
        return stuPw;
    }

    public void setStuPw(String stuPw) {
        this.stuPw = stuPw;
    }

    public String getStuSn() {
        return stuSn;
    }

    public void setStuSn(String stuSn) {
        this.stuSn = stuSn;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuSn='" + stuSn + '\'' +
                ", stuCid=" + stuCid +
                ", stuPw='" + stuPw + '\'' +
                ", photo='" + photo + '\'' +
                ", sex='" + sex + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}