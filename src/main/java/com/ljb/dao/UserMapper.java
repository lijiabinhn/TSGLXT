package com.ljb.dao;

import com.ljb.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    public User findByUserName(String username);
    public List<User> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(User user);
    public int edit(User user);
    public int delete(String ids);
}
