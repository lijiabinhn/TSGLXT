package com.ljb.dao;

import com.ljb.pojo.grade;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GradeMapper {
    public List<grade> findAll();
}
