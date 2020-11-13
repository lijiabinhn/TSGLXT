package com.ljb.dao;

import com.ljb.pojo.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TicketMapper {
    public List<Ticket> findList(Map<String, Object> queryMap);
    public List<Ticket> userList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Ticket ticket);
    public int edit(Ticket ticket);
    public int delete(String ids);
}
