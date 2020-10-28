package com.ljb.service.impl;

import com.ljb.dao.StuOpeMapper;
import com.ljb.pojo.StuOperation;
import com.ljb.service.StuOpeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuOpeServiceImpl implements StuOpeService {
    @Autowired(required = false)
    private StuOpeMapper clazzMapper;
    @Override
    public List<StuOperation> findAll() {
        return clazzMapper.findAll();
    }
}
