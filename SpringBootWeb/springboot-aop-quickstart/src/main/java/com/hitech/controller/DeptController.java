package com.hitech.controller;

import com.hitech.pojo.Dept;
import com.hitech.pojo.Result;
import com.hitech.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope("prototype")
@Lazy
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    // private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    // @RequestMapping(value = "/depts", method = RequestMethod.GET)

    public DeptController() {
        System.out.println("DeptController constructor...");
    }

    /**
     * 查询全部部门数据
     *
     * @return return a Result object.
     */
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 根据id删除部门
     *
     * @param id path variable
     * @return return a Result object.
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据id删除部门: {}", id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     *
     * @param dept
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询部门: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门: {}", dept);
        deptService.update(dept);
        return Result.success();
    }


}
