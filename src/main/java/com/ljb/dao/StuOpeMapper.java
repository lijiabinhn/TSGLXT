package com.ljb.dao;

import com.ljb.pojo.StuOperation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StuOpeMapper {
    //public int add(Clazz clazz);
    public List<StuOperation> findAll();
    public List<StuOperation> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
