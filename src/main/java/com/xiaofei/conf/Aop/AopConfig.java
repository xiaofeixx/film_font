package com.xiaofei.conf.Aop;

import com.xiaofei.conf.annotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaofei
 * @Classname AopConfig
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Component
@Aspect
@Order(2)
public class AopConfig {

    private Logger log = LoggerFactory.getLogger(AopConfig.class);

    private LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer =
            new LocalVariableTableParameterNameDiscoverer();
    @Pointcut("@annotation(com.xiaofei.conf.annotation.SysLog)")
    public void logAspect() {
    }


    @Around("logAspect()")
    public Object doAround(ProceedingJoinPoint point) {

        Object result = null;

        try {
             result = point.proceed();

            Map<String, Object> map = this.getMethodDescription(point);
            Set<String> mapSet = map.keySet();
            for (String key : mapSet) {
                log.warn(key+":{}",map.get(key));
            }

        } catch (Throwable throwable) {
            System.out.println("发生异常");
        }
        return result;
    }

    public Map<String,Object> getMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {

        Map<String,Object> map = new HashMap<>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments= joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);

        Method[] methods = targetClass.getMethods();

        for (Method method : methods) {
            if (method.getName().equals(methodName)){
                Class<?>[] clazzs = method.getParameterTypes();
                if (arguments.length == clazzs.length){
                    map.put("module",method.getAnnotation(SysLog.class).module());
                    map.put("method",method.getAnnotation(SysLog.class).methods());
                    map.put("args",this.getArgs(method,arguments));
                    String description = method.getAnnotation(SysLog.class).description();
                    if (description.isEmpty()){
                        description = "执行成功";
                    }
                    map.put("description",description);
                    break;
                }
            }
        }

        return map;
    }


    public String getArgs(Method method,Object [] argments){

        StringBuilder builder = new StringBuilder("{");
        String[] params = parameterNameDiscoverer.getParameterNames(method);

        for (int i = 0; i < params.length; i++) {
            if (!"password".equals(params[i])){
                builder.append(params[i]).append(":").append(argments[i]).append(";");
            }
        }
            return  builder.append("}").toString();
    }

}
