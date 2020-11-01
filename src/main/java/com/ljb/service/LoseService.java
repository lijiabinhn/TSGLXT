package com.ljb.service;

import com.ljb.pojo.Lose;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LoseService {
    public List<Lose> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Lose lose);
    public int edit(Lose lose);
    public int delete(String ids);
}
