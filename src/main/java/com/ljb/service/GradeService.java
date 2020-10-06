package com.ljb.service;

import com.ljb.pojo.grade;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GradeService {
    public List<grade> findAll();
}
