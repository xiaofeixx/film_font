package com.xiaofei.conf.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author xiaofei
 * @Classname CusterRoleOrAuthorzation
 * @Description 个人项目，仅供学习
 * @Date 2020/5/21 15:49
 * @Created by xiaofei
 */
public class CusterRoleOrAuthorzation extends AuthorizationFilter {

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = this.getSubject(request, response);
        //获取所需要的集合
        String[] rolesArray = ((String[]) mappedValue);
        if (rolesArray != null && rolesArray.length != 0) {
            Set<String> roles = CollectionUtils.asSet(rolesArray);
            for (String role : roles) {
                if (subject.hasRole(role)) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

}
