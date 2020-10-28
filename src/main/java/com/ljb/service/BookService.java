package com.ljb.service;

import com.ljb.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookService {
    public List<Book> findAll();
    public List<Book> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
