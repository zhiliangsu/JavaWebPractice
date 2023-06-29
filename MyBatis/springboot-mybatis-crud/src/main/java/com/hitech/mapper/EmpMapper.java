package com.hitech.mapper;

import com.hitech.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/*@Mapper注解：表示当前接口为mybatis中的Mapper接口
  程序运行时会自动创建接口的实现类对象(代理对象)，并交给Spring的IOC容器管理
*/
@Mapper
public interface EmpMapper {

    // 根据ID删除数据
    // @Delete("delete from emp where id = 17")
    // @Delete("delete from emp where id = ${id}") // 不会预编译SQL,直接拼接,性能低,不能防sql注入
    @Delete("delete from emp where id = #{id}") // 会预编译SQL,使用?占位符,性能高,可以防sql注入,更安全
    // public int delete(Integer id);
    public void delete(Integer id);

    // 新增员工
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

    // 更新员工
    @Update("update emp set username=#{username}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, " +
            "entrydate=#{entrydate}, dept_id=#{deptId}, update_time=#{updateTime} where id=#{id}")
    public void update(Emp emp);

    // 根据id查询员工
    // 方案三: 开启mybatis的驼峰命名自动映射开关 --- a_cloumn ------> aColumn (推荐)
    @Select("select * from emp where id=#{id}")
    public Emp getById(Integer id);

    // 方案一: 给字段起别名, 让别名与实体类属性一致
    /* @Select("select id, username, password, name, gender, image, job, entrydate, " +
            "dept_id deptId, create_time createTime, update_time updateTime from emp where id=#{id}")
    public Emp getById(Integer id); */

    // 方案二: 通过@Results, @Result注解手动映射封装
    /* @Results({
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from emp where id=#{id}")
    public Emp getById(Integer id); */

    // 根据条件查询员工
    /* @Select("select * from emp where name like '%${name}%' and gender=#{gender} " +
            "and entrydate between #{begin} and #{end} order by update_time desc")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end); */

    /* @Select("select * from emp where name like concat('%', #{name}, '%') and gender=#{gender} " +
            "and entrydate between #{begin} and #{end} order by update_time desc")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end); */

    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    // 动态更新员工
    public void update2(Emp emp);

    // 批量删除员工
    public void deleteByIds(List<Integer> ids);
}
