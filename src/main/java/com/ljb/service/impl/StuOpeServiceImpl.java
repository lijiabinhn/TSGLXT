package com.ljb.service.impl;

import com.ljb.dao.StuOpeMapper;
import com.ljb.pojo.StuOperation;
import com.ljb.service.StuOpeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StuOpeServiceImpl implements StuOpeService {
    @Autowired(required = false)
    private StuOpeMapper stuOpeMapper;
    @Override
    public List<StuOperation> findAll() {
        return stuOpeMapper.findAll();
    }

    @Override
    public List<StuOperation> findList(Map<String, Object> queryMap) {
        return null;
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return 0;
    }
}
