package com.ljb.dao;

import com.ljb.pojo.Contribute;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContributeMapper {
    public List<Contribute> findList(Map<String, Object> queryMap);
    public List<Contribute> userList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Contribute contribute);
}
