package com.xiaofei.entity;

import lombok.Data;

import java.util.List;

/**
 * @author xiaofei
 * @Classname Role
 * @Description 个人项目，仅供学习
 * @Date 2020/5/21 11:36
 * @Created by xiaofei
 */
@Data
public class Role {

    private int id;
    private String name;
    private String description;
    private List<Permission> permissions;

}
