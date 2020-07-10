package com.xiaofei.dao;

import com.xiaofei.entity.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author xiaofei
 * @Classname RoleMapper
 * @Description 个人项目，仅供学习
 * @Date 2020/5/21 15:00
 * @Created by xiaofei
 */
public interface RoleMapper {

    @Select("select ur.role_id,r.name,r.description from user_role as ur left join role as r " +
            "on ur.role_id = r.id where ur.user_id = {userId}")
    @Results(
            value = {
                    @Result(property = "id",column = "id"),
                    @Result(property = "name",column = "name"),
                    @Result(property = "description",column = "description"),
                    @Result(property = "permissions",column = "id",many = @Many(select = "com.xiaofei.dao.PermissionMapper.findPermissionByRoleId"))
            }
    )
     List<Role> findRolesByUserId(int userId);


}
