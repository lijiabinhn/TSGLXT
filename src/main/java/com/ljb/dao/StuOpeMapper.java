package com.ljb.dao;

import com.ljb.pojo.StuOperation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuOpeMapper {
    //public int add(Clazz clazz);
    public List<StuOperation> findAll();
}
