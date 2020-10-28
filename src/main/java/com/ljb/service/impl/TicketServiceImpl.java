package com.ljb.service.impl;

import com.ljb.pojo.Ticket;
import com.ljb.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public List<Ticket> findList(Map<String, Object> queryMap) {
        return null;
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return 0;
    }
}
