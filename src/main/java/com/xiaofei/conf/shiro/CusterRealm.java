package com.xiaofei.conf.shiro;

import com.xiaofei.dao.UserMapper;
import com.xiaofei.entity.Permission;
import com.xiaofei.entity.Role;
import com.xiaofei.entity.User;
import com.xiaofei.entity.UserQuery;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaofei
 * @Classname CusterRealm
 * @Description 个人项目，仅供学习
 * @Date 2020/5/20 16:33
 * @Created by xiaofei
 */
public class CusterRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = (String) authenticationToken.getPrincipal();
        Object credentials = authenticationToken.getCredentials();
        User user = userMapper.findUserByName(name);
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }

    /**
     * 权限验证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserQuery userQuery = (UserQuery) principalCollection.getPrimaryPrincipal();
        User user = userMapper.findUserByName(userQuery.getName());
        List<String> stringRoleList = new ArrayList<>();
        List<String> stringPermissionList = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {

            stringRoleList.add(role.getName());
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                if (permission != null) {
                    stringPermissionList.add(permission.getName());
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);
        return simpleAuthorizationInfo;
    }


}
