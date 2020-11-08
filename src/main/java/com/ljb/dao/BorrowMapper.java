package com.ljb.dao;

import com.ljb.pojo.Borrow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BorrowMapper {
    public List<Borrow> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Borrow borrow);
    public int edit(Borrow borrow);
    public int delete(String ids);
    public String userAdd(Borrow borrow);
    public String showInfo(Borrow borrow);
}
