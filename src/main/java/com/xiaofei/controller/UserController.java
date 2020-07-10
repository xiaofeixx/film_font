package com.xiaofei.controller;

import com.xiaofei.entity.UserQuery;
import com.xiaofei.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname UserController
 * @Description 个人项目，仅供学习
 * @Date 2020/5/20 16:26
 * @Created by xiaofei
 */
@RestController
@RequestMapping("/pub")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> findUserByName(@RequestBody UserQuery userQuery) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken(userQuery.getName(), userQuery.getPassword(), this.getClass().getName());
        Map<String, Object> info = new HashMap<>();
        try {
            subject.login(token);
            info.put("sessionId", subject.getSession().getId());
            info.put("msg", "登录成功");
        } catch (AuthenticationException e) {
            info.put("msg", "用户名或密码错误");
            return info;
        }
        return info;
    }
}
