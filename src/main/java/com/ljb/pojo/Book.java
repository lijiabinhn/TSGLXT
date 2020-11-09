package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Book {
    private int bookNo;//书号
    private String bookName;//书名
    private String authors;//作者
    private String publish;//出版社
    private String buyTime;//购买时间
    private String isBorrow;//是否借阅
    private String isOrder;//是否预约

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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(String isBorrow) {
        this.isBorrow = isBorrow;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookNo=" + bookNo +
                ", bookName='" + bookName + '\'' +
                ", Author='" + authors + '\'' +
                ", Publish='" + publish + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", isBorrow='" + isBorrow + '\'' +
                ", isOrder='" + isOrder + '\'' +
                '}';
    }
}
