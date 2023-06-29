package com.hitech;

import com.hitech.mapper.EmpMapper;
import com.hitech.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    // 根据id删除员工
    @Test
    public void testDelete() {
        // int delete = empMapper.delete(16);
        // System.out.println(delete);
        empMapper.delete(16);
    }

    // 新增员工
    @Test
    public void testInsert() {
        Emp emp = new Emp();
        emp.setUsername("Tom3");
        emp.setName("汤姆3");
        emp.setGender((short) 1);
        emp.setImage("1.png");
        emp.setJob((short) 1);
        emp.setEntrydate(LocalDate.of(2000, 01, 01));
        emp.setDeptId(1);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
        System.out.println(emp.getId());
    }

    // 更新员工
    @Test
    public void testUpdate() {
        Emp emp = new Emp();
        emp.setId(18);
        emp.setUsername("Tom1");
        emp.setName("汤姆1");
        emp.setGender((short) 1);
        emp.setImage("1.png");
        emp.setJob((short) 1);
        emp.setEntrydate(LocalDate.of(2000, 01, 01));
        emp.setDeptId(1);
        // emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    // 根据id查询员工
    @Test
    public void testGetById() {
        Emp emp = empMapper.getById(20);
        System.out.println(emp);
    }

    // 根据条件查询员工
    @Test
    public void testList() {
        // List<Emp> empList = empMapper.list("张", (short) 1, LocalDate.of(2010, 1, 1), LocalDate.of(2020, 1, 1));
        // List<Emp> empList = empMapper.list("张", (short) 1 , null, null);
        // List<Emp> empList = empMapper.list("张", null , null, null);
        // List<Emp> empList = empMapper.list(null, (short) 1 , null, null);
        List<Emp> empList = empMapper.list(null, null, null, null);
        System.out.println(empList);
    }

    // 动态sql更新员工 - 更新ID为18的员工 username 更新为 Tom111, name更新为 汤姆111, gender更新为2
    @Test
    public void testUpdate2() {
        Emp emp = new Emp();
        emp.setId(18);
        emp.setUsername("Tom222");
        // emp.setName("汤姆111");
        // emp.setGender((short) 2);
        // emp.setImage("1.png");
        // emp.setJob((short) 1);
        // emp.setEntrydate(LocalDate.of(2000, 01, 01));
        // emp.setDeptId(1);
        // emp.setCreateTime(LocalDateTime.now());
        // emp.setUpdateTime(LocalDateTime.now());

        empMapper.update2(emp);
    }

    // 批量删除员工
    @Test
    public void testDeleteByIds() {
        empMapper.deleteByIds(Arrays.asList(18, 19, 20));
    }
}
