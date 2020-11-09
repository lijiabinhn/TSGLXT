package com.ljb.service.impl;

import com.ljb.dao.ContributeMapper;
import com.ljb.pojo.Contribute;
import com.ljb.service.ContributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContributeServiceImpl implements ContributeService {

    @Autowired(required = false)
    private ContributeMapper contributeMapper;


    @Override
    public List<Contribute> findList(Map<String, Object> queryMap) {
        return contributeMapper.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return contributeMapper.getTotal(queryMap);
    }

    @Override
    public int add(Contribute contribute) {
        return contributeMapper.add(contribute);
    }
}
