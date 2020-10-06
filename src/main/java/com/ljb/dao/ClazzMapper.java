package com.ljb.dao;

import com.ljb.pojo.Clazz;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClazzMapper {
    //public int add(Clazz clazz);
    public List<Clazz> findAll();
}
