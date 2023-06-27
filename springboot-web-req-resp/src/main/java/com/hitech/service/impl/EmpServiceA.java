package com.hitech.service.impl;

import com.hitech.dao.EmpDao;
import com.hitech.pojo.Emp;
import com.hitech.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

// @Component
// @Primary
@Service
public class EmpServiceA implements EmpService {
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {
        // 1.调用dao,获取数据
        List<Emp> empList = empDao.listEmp();

        // 2.数据转换: gender, job
        empList.stream().forEach(emp -> {
            // 转换gender
            if ("1".equals(emp.getGender())) {
                emp.setGender("男");
            } else if ("2".equals(emp.getGender())) {
                emp.setGender("女");
            }
            // 转换job
            if ("1".equals(emp.getJob())) {
                emp.setJob("讲师");
            } else if ("2".equals(emp.getJob())) {
                emp.setJob("班主任");
            } else if ("3".equals(emp.getJob())) {
                emp.setJob("就业指导");
            }
        });

        return empList;
    }
}
