package com.hitech.service.impl;

import com.hitech.aop.MyLog;
import com.hitech.mapper.DeptMapper;
import com.hitech.pojo.Dept;
import com.hitech.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @MyLog
    @Override
    public List<Dept> list() {
        List<Dept> deptList = deptMapper.list();
        //模拟异常
        // int num = 10/0;
        return deptList;
    }

    /**
     * 根据部门id,删除部门以及部门下的所有员工
     *
     * @param id
     */
    @MyLog
    @Transactional//(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) throws Exception {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        Dept dept = deptMapper.getById(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
