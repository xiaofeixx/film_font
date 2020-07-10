package com.xiaofei.conf.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname ShiroConfig
 * @Description 个人项目，仅供学习
 * @Date 2020/5/20 16:07
 * @Created by xiaofei
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置factorBean Bean
     * 配置了自定义过滤器和过滤器链
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());
        factoryBean.setLoginUrl("/pub/need_login");
        factoryBean.setSuccessUrl("/");
        factoryBean.setUnauthorizedUrl("/pub/not_permit");

        //自定义Filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("roleOrFilter", new CusterRoleOrAuthorzation());
        factoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //退出过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        //匿名访问过滤器
        filterChainDefinitionMap.put("/pub/**", "anon");
        //登录才可以访问
        filterChainDefinitionMap.put("/authc/**", "authc");
        //管理员角色才可以访问
        filterChainDefinitionMap.put("/admin/**", "roleOrFilter[admin]");

        filterChainDefinitionMap.put("/goods/update", "perms[video_update]");

        filterChainDefinitionMap.put("/**", "authc");

        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    /**
     * 配置SecurityManager
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setRealm(custerRealm());
        securityManager.setSessionManager(custerSessionManager());
        return securityManager;
    }

    /**
     * 配置自定义Realm
     *
     * @return
     */
    @Bean
    public CusterRealm custerRealm() {

        CusterRealm custerRealm = new CusterRealm();
        custerRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return custerRealm;
    }

    /**
     * 配置自定义SessionManager
     * sessionId在30分钟后过期
     * redis缓存sessionId
     *
     * @return
     */
    @Bean
    public CusterSessionManager custerSessionManager() {
        CusterSessionManager sessionManager = new CusterSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000L);
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 配置RedisManager
     *
     * @return
     */
    @Bean
    public RedisManager getRedisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }

    /**
     * 配置Cache实现类,在这里利用redis
     *
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        redisCacheManager.setExpire(20);
        return redisCacheManager;
    }

    /**
     * 自定义session持久化,利用redis
     *
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        redisSessionDAO.setSessionIdGenerator(new CusterSessionId());
        return redisSessionDAO;
    }

    /**
     * 散列算法配置,对密码加密
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }

    /**
     * 管理shiro的bean的生命周期
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 令shiro的注解生效
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 扫描所有的Advisor（通知器），将符合条件的加入切入点bean中
     *
     * @return
     * @DependsOn ioc中必须有该bean才初始化下面的bean
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }


}
