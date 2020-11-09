package com.ljb.service;

import com.ljb.pojo.Contribute;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ContributeService {
    public List<Contribute> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Contribute contribute);
}
