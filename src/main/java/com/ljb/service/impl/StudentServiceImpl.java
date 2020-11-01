package com.ljb.service.impl;

import com.ljb.dao.StudentMapper;
import com.ljb.pojo.Student;
import com.ljb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired(required = false)
    private StudentMapper studentMapper;

    public Student findByUserName(String username) {
        return studentMapper.findByUserName(username);
    }

    @Override
    public int add(Student student) {
        return studentMapper.add(student);
    }

    @Override
    public int edit(Student student) {
        return studentMapper.edit(student);
    }

    @Override
    public int delete(String ids) {
        return studentMapper.delete(ids);
    }

    @Override
    public List<Student> findList(Map<String, Object> queryMap) {
        return studentMapper.findList(queryMap);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return studentMapper.getTotal(queryMap);
    }
}
