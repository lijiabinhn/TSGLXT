package com.ljb.service.impl;

import com.ljb.dao.GradeMapper;
import com.ljb.pojo.grade;
import com.ljb.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired(required = false)
    private GradeMapper gradeMapper;
    @Override
    public List<grade> findAll() {
        return gradeMapper.findAll();
    }
}
