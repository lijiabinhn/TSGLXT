package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Ticket {
    private int ticNo;
    private int stuNo;
    private String stuName;
    private String stuClazz;//前面的班级ID用的是int
    private String bookName;
    private String ticTime;//违期时长
    private String ticMoney;//应付金额
    private String isMoney;//是否付款
    private String payWay;//付款方式

    public int getTicNo() {
        return ticNo;
    }

    public void setTicNo(int ticNo) {
        this.ticNo = ticNo;
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

    public String getStuClazz() {
        return stuClazz;
    }

    public void setStuClazz(String stuClazz) {
        this.stuClazz = stuClazz;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTicTime() {
        return ticTime;
    }

    public void setTicTime(String ticTime) {
        this.ticTime = ticTime;
    }

    public String getTicMoney() {
        return ticMoney;
    }

    public void setTicMoney(String ticMoney) {
        this.ticMoney = ticMoney;
    }

    public String getIsMoney() {
        return isMoney;
    }

    public void setIsMoney(String isMoney) {
        this.isMoney = isMoney;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticNo=" + ticNo +
                ", stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuClazz='" + stuClazz + '\'' +
                ", bookName='" + bookName + '\'' +
                ", ticTime='" + ticTime + '\'' +
                ", ticMoney='" + ticMoney + '\'' +
                ", isMoney='" + isMoney + '\'' +
                ", payWay='" + payWay + '\'' +
                '}';
    }
}
