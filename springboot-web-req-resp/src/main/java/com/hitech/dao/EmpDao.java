package com.hitech.dao;

import com.hitech.pojo.Emp;

import java.util.List;

// 数据访问层接口(制定标准)
public interface EmpDao {
    // 获取员工列表数据
    public List<Emp> listEmp();
}
