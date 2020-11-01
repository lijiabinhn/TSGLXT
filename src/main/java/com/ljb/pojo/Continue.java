package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Continue {
    int ctNo;
    int stuNo;
    String stuName;
    int bookNo;
    String bookName;
    int delayTime;//续借时长

    public int getCtNo() {
        return ctNo;
    }

    public void setCtNo(int ctNo) {
        this.ctNo = ctNo;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getBookNo() {
        return bookNo;
    }

    public void setBookNo(int bookNo) {
        this.bookNo = bookNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    @Override
    public String toString() {
        return "Continue{" +
                "ctNo=" + ctNo +
                ", stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", bookNo=" + bookNo +
                ", bookName='" + bookName + '\'' +
                ", delayTime=" + delayTime +
                '}';
    }
}
