package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Lose {
    int lbNo;
    int stuNo;
    String stuName;
    int bookNo;
    String bookName;
    String borrowTime;
    String loseTime;

    public int getLbNo() {
        return lbNo;
    }

    public void setLbNo(int lbNo) {
        this.lbNo = lbNo;
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

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getLoseTime() {
        return loseTime;
    }

    public void setLoseTime(String loseTime) {
        this.loseTime = loseTime;
    }

    @Override
    public String toString() {
        return "Lose{" +
                "lbNo=" + lbNo +
                ", stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", bookNo=" + bookNo +
                ", bookName='" + bookName + '\'' +
                ", borrowTime='" + borrowTime + '\'' +
                ", loseTime='" + loseTime + '\'' +
                '}';
    }
}
