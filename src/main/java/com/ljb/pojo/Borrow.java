package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Borrow {
    int orNo;//编号
    String stuName;
    int stuNo;
    String stuClazz;
    int bookNo;
    String bookName;
    String borrowTime;//借阅时间
    String returnTime;//应还时间

    public int getOrNo() {
        return orNo;
    }

    public void setOrNo(int orNo) {
        this.orNo = orNo;
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

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "roNo=" + orNo +
                ", stuName='" + stuName + '\'' +
                ", stuNo=" + stuNo +
                ", stuClazz='" + stuClazz + '\'' +
                ", bookNo=" + bookNo +
                ", bookName='" + bookName + '\'' +
                ", borrowTime='" + borrowTime + '\'' +
                ", returnTime='" + returnTime + '\'' +
                '}';
    }
}
