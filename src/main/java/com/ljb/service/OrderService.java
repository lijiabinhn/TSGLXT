package com.ljb.service;

import com.ljb.pojo.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {
    public List<Order> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Order order);
    public int edit(Order order);
    public int delete(String ids);
}
