package com.ljb.service.impl;

import com.ljb.dao.UserMapper;
import com.ljb.pojo.User;
import com.ljb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public List<User> findList(Map<String, Object> queryMap) {
        return userMapper.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return userMapper.getTotal(queryMap);
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int edit(User user) {
        return userMapper.edit(user);
    }

    @Override
    public int delete(String ids) {
        return userMapper.delete(ids);
    }
}
