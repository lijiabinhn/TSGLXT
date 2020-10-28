package com.ljb.service;

import com.ljb.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    public List<Book> findAll();
}
