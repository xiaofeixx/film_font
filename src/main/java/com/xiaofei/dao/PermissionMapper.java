package com.xiaofei.dao;

import com.xiaofei.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaofei
 * @Classname Permission
 * @Description 个人项目，仅供学习
 * @Date 2020/5/21 15:01
 * @Created by xiaofei
 */
public interface PermissionMapper {

    @Select("select  p.id,p.name,p.url from role_permission rp left join permission p " +
            "on rp.per_id = p.id where role_id = #{id}")
     List<Permission> findPermissionByRoleId(int id);

}
