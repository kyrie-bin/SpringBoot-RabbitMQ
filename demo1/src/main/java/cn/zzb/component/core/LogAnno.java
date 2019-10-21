package cn.zzb.component.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Kyrie
 * @Date 2019/7/4
 */
// 方法注解
@Target(ElementType.METHOD)
// 运行时可见
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnno {
    //记录日志的操作类型
    String operateType();
}
