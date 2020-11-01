package com.ljb.service;

import com.ljb.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookService {
    public List<Book> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int add(Book book);
    public int edit(Book book);
    public int delete(String ids);
}
