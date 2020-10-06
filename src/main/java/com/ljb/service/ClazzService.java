package com.ljb.service;

import com.ljb.pojo.Clazz;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClazzService {
    public List<Clazz> findAll();
}
