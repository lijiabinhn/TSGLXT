package com.ljb.dao;

import com.ljb.pojo.Return;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReturnMapper {
    public List<Return> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Return returns);
    public int edit(Return returns);
    public int delete(String ids);
    public String userAdd(Return returns);
}
