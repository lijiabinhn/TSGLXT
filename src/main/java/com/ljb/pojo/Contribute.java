package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Contribute {
    private int cNo;
    private String cName;
    private String cSex;
    private String cTle;
    private String cBook;
    private String cTime;

    public int getcNo() {
        return cNo;
    }

    public void setcNo(int cNo) {
        this.cNo = cNo;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcSex() {
        return cSex;
    }

    public void setcSex(String cSex) {
        this.cSex = cSex;
    }

    public String getcTle() {
        return cTle;
    }

    public void setcTle(String cTle) {
        this.cTle = cTle;
    }

    public String getcBook() {
        return cBook;
    }

    public void setcBook(String cBook) {
        this.cBook = cBook;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    @Override
    public String toString() {
        return "Contribute{" +
                "cNo=" + cNo +
                ", cName='" + cName + '\'' +
                ", cSex='" + cSex + '\'' +
                ", cTle='" + cTle + '\'' +
                ", cBook='" + cBook + '\'' +
                ", cTime='" + cTime + '\'' +
                '}';
    }
}
