package com.ljb.service.impl;

import com.ljb.dao.LoseMapper;
import com.ljb.pojo.Lose;
import com.ljb.service.LoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoseServiceImpl implements LoseService {

    @Autowired(required =false)
    private LoseMapper loseMapper;

    @Override
    public List<Lose> findList(Map<String, Object> queryMap) {
        return loseMapper.findList(queryMap);
    }

    @Override
    public List<Lose> userList(Map<String, Object> queryMap) {
        return loseMapper.userList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return loseMapper.getTotal(queryMap);
    }

    @Override
    public int add(Lose lose) {
        return loseMapper.add(lose);
    }

    @Override
    public int edit(Lose lose) {
        return loseMapper.edit(lose);
    }

    @Override
    public int delete(String ids) {
        return loseMapper.delete(ids);
    }

    @Override
    public String userAdd(Lose lose) {
        return loseMapper.userAdd(lose);
    }
}
