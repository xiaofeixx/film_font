package com.xiaofei.conf.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @author xiaofei
 * @Classname CusterSessionManager
 * @Description 个人项目，仅供学习
 * @Date 2020/5/21 15:19
 * @Created by xiaofei
 */
public class CusterSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "token";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if (sessionId != null){

            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "cookie");

            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return sessionId;
        } else {
            return super.getSessionId(request,response);
        }


    }
}
