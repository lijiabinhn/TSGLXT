package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Return {
    int rNo;
    int bookNo;
    int stuNo;
    String borrowTime;
    String returnTime;
    String stuName;

    public int getrNo() {
        return rNo;
    }

    public void setrNo(int rNo) {
        this.rNo = rNo;
    }

    public int getBookNo() {
        return bookNo;
    }

    public void setBookNo(int bookNo) {
        this.bookNo = bookNo;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
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

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "Return{" +
                "rNo=" + rNo +
                ", bookNo=" + bookNo +
                ", stuNo=" + stuNo +
                ", borrowTime='" + borrowTime + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", stuName='" + stuName + '\'' +
                '}';
    }
}
