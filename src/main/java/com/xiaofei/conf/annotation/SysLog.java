package com.xiaofei.conf.annotation;

import java.lang.annotation.*;

/**
 * @author xiaofei
 * @Classname SysLog
 * @Description 个人项目，仅供学习
 * @Date 2020/5/21 17:48
 * @Created by xiaofei
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String module() default "";
    String methods() default "";
    String description() default "";
}
