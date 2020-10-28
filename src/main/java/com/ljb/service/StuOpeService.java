package com.ljb.service;

import com.ljb.pojo.StuOperation;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StuOpeService {
    public List<StuOperation> findAll();
}
