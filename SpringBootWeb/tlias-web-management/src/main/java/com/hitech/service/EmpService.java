package com.hitech.service;

import com.hitech.pojo.Emp;
import com.hitech.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * 条件分页查询
     *
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 删除用户
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    void add(Emp emp);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 修改员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 用户登录
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
