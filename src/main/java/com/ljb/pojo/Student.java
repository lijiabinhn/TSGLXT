package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Student {
    private Integer stuId;
    private String stuSn;
    private String stuCid;
    private String stuPw;
    private String photo;
    private String sex;
    private String remark;
    private String per;//借阅权限
    private int num;//借阅数量

    private static String userName = null;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Student.userName = userName;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuSn() {
        return stuSn;
    }

    public void setStuSn(String stuSn) {
        this.stuSn = stuSn;
    }

    public String getStuCid() {
        return stuCid;
    }

    public void setStuCid(String stuCid) {
        this.stuCid = stuCid;
    }

    public String getStuPw() {
        return stuPw;
    }

    public void setStuPw(String stuPw) {
        this.stuPw = stuPw;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuSn='" + stuSn + '\'' +
                ", stuCid='" + stuCid + '\'' +
                ", stuPw='" + stuPw + '\'' +
                ", photo='" + photo + '\'' +
                ", sex='" + sex + '\'' +
                ", remark='" + remark + '\'' +
                ", per='" + per + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
