package com.ljb.service.impl;

import com.ljb.dao.ClazzMapper;
import com.ljb.pojo.Clazz;
import com.ljb.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired(required = false)
    private ClazzMapper clazzMapper;
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
