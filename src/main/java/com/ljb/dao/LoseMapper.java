package com.ljb.dao;

import com.ljb.pojo.Lose;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoseMapper {
    public List<Lose> findList(Map<String, Object> queryMap);
    public List<Lose> userList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Lose lose);
    public int edit(Lose lose);
    public int delete(String ids);
    public String userAdd(Lose lose);
}
