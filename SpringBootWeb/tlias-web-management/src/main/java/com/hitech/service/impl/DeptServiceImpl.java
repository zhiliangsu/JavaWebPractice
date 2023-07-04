package com.hitech.service.impl;

import com.hitech.mapper.DeptMapper;
import com.hitech.mapper.EmpMapper;
import com.hitech.pojo.Dept;
import com.hitech.pojo.DeptLog;
import com.hitech.service.DeptLogService;
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
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        List<Dept> deptList = deptMapper.list();
        return deptList;
    }

    /**
     * 根据部门id,删除部门以及部门下的所有员工
     *
     * @param id
     */
    @Transactional//(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) throws Exception {
        try {
            // 根据id删除部门
            deptMapper.deleteById(id);

            // 模拟异常
            int i = 1 / 0;
        /* if (true) {
            throw new Exception("出异常了...");
        } */

            // 根据部门id删除部门下所有的员工
            empMapper.deleteByDeptId(id);
        } finally {
            // 不论是否有异常, 最终都要执行的代码: 记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的是" + id + "号部门");
            deptLogService.insert(deptLog);
        }
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
