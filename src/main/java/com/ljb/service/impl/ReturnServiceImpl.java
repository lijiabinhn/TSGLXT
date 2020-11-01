package com.ljb.service.impl;

import com.ljb.dao.ReturnMapper;
import com.ljb.pojo.Return;
import com.ljb.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired(required = false)
    private ReturnMapper returnMapper;

    @Override
    public List<Return> findList(Map<String, Object> queryMap) {
        return returnMapper.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return returnMapper.getTotal(queryMap);
    }

    @Override
    public int add(Return returns) {
        return returnMapper.add(returns);
    }

    @Override
    public int edit(Return returns) {
        return returnMapper.edit(returns);
    }

    @Override
    public int delete(String ids) {
        return returnMapper.delete(ids);
    }
}
