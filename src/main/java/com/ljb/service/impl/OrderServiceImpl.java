package com.ljb.service.impl;

import com.ljb.dao.OrderMapper;
import com.ljb.pojo.Order;
import com.ljb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Override
    public List<Order> findList(Map<String, Object> queryMap) {
        return orderMapper.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return orderMapper.getTotal(queryMap);
    }

    @Override
    public int add(Order order) {
        return orderMapper.add(order);
    }

    @Override
    public int edit(Order order) {
        return orderMapper.edit(order);
    }

    @Override
    public int delete(String ids) {
        return orderMapper.delete(ids);
    }

    @Override
    public String userAdd(Order order) {
        return orderMapper.userAdd(order);
    }

    @Override
    public String showInfo(Order order) {
        return orderMapper.showInfo(order);
    }
}
