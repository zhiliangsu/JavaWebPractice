package com.hitech.mapper;

import com.hitech.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id, name, age, gender, phone from user")
    public List<User> list();
}
