package com.hitech.service;

import com.hitech.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    /**
     * 查询部门数据
     * @return
     */
    List<Dept> list();

    /**
     * 根据id删除部门
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    Dept getById(Integer id);

    /**
     * 修改部门
     * @param dept
     */
    void update(Dept dept);
}
