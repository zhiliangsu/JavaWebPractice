package com.hitech.controller;

import com.hitech.pojo.Emp;
import com.hitech.pojo.Result;
import com.hitech.service.EmpService;
import com.hitech.service.impl.EmpServiceA;
import com.hitech.utils.XmlParserUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    // @Autowired
    // @Qualifier("empServiceA")
    @Resource(name = "empServiceB")
    /* @Autowired 与 @Resource的区别
        - @Autowired 是spring框架提供的注解，而@Resource是JDK提供的注解
        - @Autowired 默认是按照类型注入，而@Resource是按照名称注入
     */
    private EmpService empService;

    @RequestMapping("/listEmp")
    public Result listEmp() {
        // 1.调用service层,获取数据
        List<Emp> empList = empService.listEmp();

        // 3.返回响应数据
        return Result.success(empList);

        /* // 1.加载xml文件并解析
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);

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

        // 3.返回响应数据
        return Result.success(empList); */
    }
}
