package com.hitech.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hitech.anno.Log;
import com.hitech.mapper.EmpMapper;
import com.hitech.pojo.Emp;
import com.hitech.pojo.PageBean;
import com.hitech.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // 1.设置分页参数
        PageHelper.startPage(page, pageSize);

        // 2.执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

        // 3.封装并返回
        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }

    /* @Override
    public PageBean page(Integer page, Integer pageSize) {
        // 1.获取总记录数
        Long count = empMapper.count();

        // 2.获取数据列表

        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.page(start, pageSize);

        // 3.封装并返回
        return new PageBean(count, empList);
    } */
}
