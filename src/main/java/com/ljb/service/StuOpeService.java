package com.ljb.service;

import com.ljb.pojo.StuOperation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StuOpeService {
    public List<StuOperation> findAll();
    public List<StuOperation> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
