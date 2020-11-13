package com.ljb.service.impl;

import com.ljb.dao.TicketMapper;
import com.ljb.pojo.Ticket;
import com.ljb.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired(required = false)
    private TicketMapper ticketMapper;

    @Override
    public List<Ticket> findList(Map<String, Object> queryMap) {
        return ticketMapper.findList(queryMap);
    }

    @Override
    public List<Ticket> userList(Map<String, Object> queryMap) {
        return ticketMapper.userList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return ticketMapper.getTotal(queryMap);
    }

    @Override
    public int add(Ticket ticket) {
        return ticketMapper.add(ticket);
    }

    @Override
    public int edit(Ticket ticket) {
        return ticketMapper.edit(ticket);
    }

    @Override
    public int delete(String ids) {
        return ticketMapper.delete(ids);
    }
}
