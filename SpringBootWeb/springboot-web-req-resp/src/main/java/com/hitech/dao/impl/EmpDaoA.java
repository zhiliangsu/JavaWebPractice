package com.hitech.dao.impl;

import com.hitech.dao.EmpDao;
import com.hitech.pojo.Emp;
import com.hitech.utils.XmlParserUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Component
@Repository("daoA")
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        // 1.加载xml文件并解析
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);

        return empList;
    }
}
