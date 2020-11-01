package com.ljb.service.impl;

import com.ljb.dao.BookMapper;
import com.ljb.pojo.Book;
import com.ljb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired(required = false)
    private BookMapper bookMapper;


    @Override
    public List<Book> findList(Map<String, Object> queryMap) {
        return bookMapper.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return bookMapper.getTotal(queryMap);
    }

    @Override
    public int add(Book book) {
        return bookMapper.add(book);
    }

    @Override
    public int edit(Book book) {
        return bookMapper.edit(book);
    }

    @Override
    public int delete(String ids) {
        return bookMapper.delete(ids);
    }
}
