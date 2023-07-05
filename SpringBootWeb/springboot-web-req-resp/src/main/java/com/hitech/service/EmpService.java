package com.hitech.service;

import com.hitech.pojo.Emp;

import java.util.List;

// 业务逻辑接口(制定业务标准)
public interface EmpService {
    // 获取员工列表
    List<Emp> listEmp();
}
