package com.ljb.service.impl;

import com.ljb.dao.BorrowMapper;
import com.ljb.pojo.Borrow;
import com.ljb.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired(required = false)
    private BorrowMapper borrowMapper;

    @Override
    public List<Borrow> findList(Map<String, Object> queryMap) {
        return borrowMapper.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return borrowMapper.getTotal(queryMap);
    }

    @Override
    public int add(Borrow borrow) {
        return borrowMapper.add(borrow);
    }

    @Override
    public int edit(Borrow borrow) {
        return borrowMapper.edit(borrow);
    }

    @Override
    public int delete(String ids) {
        return borrowMapper.delete(ids);
    }

    @Override
    public String userAdd(Borrow borrow) {
        return borrowMapper.userAdd(borrow);
    }

    @Override
    public String showInfo(Borrow borrow) {
        return borrowMapper.showInfo(borrow);
    }
}
