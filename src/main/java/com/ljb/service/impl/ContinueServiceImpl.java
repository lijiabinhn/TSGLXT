package com.ljb.service.impl;

import com.ljb.dao.ContinueMapper;
import com.ljb.pojo.Continue;
import com.ljb.service.ContinueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContinueServiceImpl implements ContinueService {


    @Autowired(required = false)
    private ContinueMapper continueMapper;

    @Override
    public List<Continue> findList(Map<String, Object> queryMap) {
        return continueMapper.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return continueMapper.getTotal(queryMap);
    }

    @Override
    public int add(Continue continues) {
        return continueMapper.add(continues);
    }

    @Override
    public int edit(Continue continues) {
        return continueMapper.edit(continues);
    }

    @Override
    public int delete(String ids) {
        return continueMapper.delete(ids);
    }

    @Override
    public String userAdd(Continue continues) {
        return continueMapper.userAdd(continues);
    }
}
