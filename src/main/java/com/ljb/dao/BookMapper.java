package com.ljb.dao;

import com.ljb.pojo.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface BookMapper {
    public List<Book> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Book book);
    public int edit(Book book);
    public int delete(String ids);
}
