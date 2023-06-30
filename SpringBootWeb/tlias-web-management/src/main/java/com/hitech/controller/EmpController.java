package com.hitech.controller;

import com.hitech.pojo.Emp;
import com.hitech.pojo.PageBean;
import com.hitech.pojo.Result;
import com.hitech.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 条件分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询: {}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /**
     * 删除员工
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除操作: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 新增员工
     *
     * @param emp
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工操作: {}", emp);
        empService.add(emp);
        return Result.success();
    }

}
