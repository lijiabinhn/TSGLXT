package com.ljb.service;

import com.ljb.pojo.Return;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReturnService {
    public List<Return> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Return returns);
    public int edit(Return returns);
    public int delete(String ids);
    public String userAdd(Return returns);
}
