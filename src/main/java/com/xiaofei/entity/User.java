package com.xiaofei.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaofei
 * @Classname User
 * @Description 个人项目，仅供学习
 * @Date 2020/5/20 16:09
 * @Created by xiaofei
 */
@Data
public class User implements Serializable {

    private int id;
    private String name;         //用户名
    private String password;     //密码
    private int age;             //年龄
    private String sex;          //性别
    private boolean superUser;   //会员
    private String phone;        //手机号
    private String email;        //邮箱
    private List<Role> roles;    //角色信息

}
