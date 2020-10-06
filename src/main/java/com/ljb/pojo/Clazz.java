package com.ljb.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Clazz {
    private Integer cid;
    private Integer claGid;
    private String cname;
    private String remark;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getClaGid() {
        return claGid;
    }

    public void setClaGid(Integer claGid) {
        this.claGid = claGid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", claGid=" + claGid +
                ", cname='" + cname + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
