package com.ljb.service;

import com.ljb.pojo.Borrow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BorrowService {
    public List<Borrow> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Borrow borrow);
    public int edit(Borrow borrow);
    public int delete(String ids);
}
