package com.ljb.dao;

import com.ljb.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {
    public List<Order> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Order order);
    public int edit(Order order);
    public int delete(String ids);
    public String userAdd(Order order);
    public String showInfo(Order order);
}
