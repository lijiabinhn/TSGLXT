package com.ljb.service;

import com.ljb.pojo.Continue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ContinueService {
    public List<Continue> findList(Map<String, Object> queryMap);
    public List<Continue> userList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Continue continues);
    public int edit(Continue continues);
    public int delete(String ids);
    public String userAdd(Continue continues);
}
