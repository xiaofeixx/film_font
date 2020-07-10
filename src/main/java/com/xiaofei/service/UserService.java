package com.xiaofei.service;

import com.xiaofei.entity.User;

/**
 * @author xiaofei
 * @Classname UserService
 * @Description 个人项目，仅供学习
 * @Date 2020/5/20 16:22
 * @Created by xiaofei
 */

public interface UserService {

    public User findUserByName(String name);
}
