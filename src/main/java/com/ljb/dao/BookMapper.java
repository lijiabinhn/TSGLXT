package com.ljb.dao;

import com.ljb.pojo.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookMapper {
    public List<Book> findAll();
}
