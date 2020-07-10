package com.xiaofei.service.iml;

import com.xiaofei.dao.UserMapper;
import com.xiaofei.entity.User;
import com.xiaofei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaofei
 * @Classname UserServiceIml
 * @Description 个人项目，仅供学习
 * @Date 2020/5/20 16:24
 * @Created by xiaofei
 */
@Service
public class UserServiceIml implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }
}

