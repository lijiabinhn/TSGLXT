package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Order {
    int yNo;
    String stuName;
    int stuNo;
    String stuClazz;
    int bookNo;
    String bookName;
    String orderTime;//预约时间
    String isBorrow;//是否可借

    public int getyNo() {
        return yNo;
    }

    public void setyNo(int yNo) {
        this.yNo = yNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuClazz() {
        return stuClazz;
    }

    public void setStuClazz(String stuClazz) {
        this.stuClazz = stuClazz;
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

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(String isBorrow) {
        this.isBorrow = isBorrow;
    }

    @Override
    public String toString() {
        return "Order{" +
                "yNo=" + yNo +
                ", stuName='" + stuName + '\'' +
                ", stuNo=" + stuNo +
                ", stuClazz='" + stuClazz + '\'' +
                ", bookNo=" + bookNo +
                ", bookName='" + bookName + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", isBorrow='" + isBorrow + '\'' +
                '}';
    }
}
