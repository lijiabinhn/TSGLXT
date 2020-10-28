package com.ljb.service;

import com.ljb.pojo.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TicketService {
    public List<Ticket> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
