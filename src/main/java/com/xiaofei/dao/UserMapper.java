package com.xiaofei.dao;

import com.xiaofei.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Service;

/**
 * @author xiaofei
 * @Classname UserMapper
 * @Description 个人项目，仅供学习
 * @Date 2020/5/20 16:18
 * @Created by xiaofei
 */
public interface UserMapper {

    @Select("select name,password from user where name=#{name}")
    public User findUserByName(String name);

    @Select("select id,name,password from user where name=#{name}")
    @Results(
            value = {
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "password",column = "password"),
            @Result(property = "roles",column = "id",many = @Many(select = "com.xiaofei.dao.RoleMapper.findRolesByUserId",fetchType = FetchType.DEFAULT))
    })
     User findAllLetterByName(String name);


}
