package com.xiaofei.conf.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author xiaofei
 * @Classname CusterSessionId
 * @Description 个人项目，仅供学习
 * @Date 2020/5/21 15:24
 * @Created by xiaofei
 */
public class CusterSessionId implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        return "ff_video"+ UUID.randomUUID().toString().replace("-","");
    }
}
