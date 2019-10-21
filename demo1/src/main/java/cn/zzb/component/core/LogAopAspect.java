package cn.zzb.component.core;

import cn.zzb.domain.Log;
import cn.zzb.service.user.ILogService;
import cn.zzb.util.HttpContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * AOP实现日志
 *
 * @Author Kyrie
 * @Date 2019/7/4
 */
@Order(3)
//@Component
@Aspect
public class LogAopAspect {

    @Autowired
    private ILogService logService;

    @Pointcut("@annotation(cn.zzb.component.core.LogAnno)")
    public void Annotation() {

    }

    /**
     * 环绕通知记录日志通过注解匹配到需要增加日志功能的方法
     *
     * @param pjp
     * @return
     */
    @Around("Annotation()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        //1.方法执行前的处理,相当于前置通知
        //获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        //获取方法
        Method method = methodSignature.getMethod();
        //获取方法上面的注解
        LogAnno anno = method.getAnnotation(LogAnno.class);
        //获取操作描述的属性值
        String operateType = anno.operateType();
        //创建一个日志对象(准备记录日志)
        Log log = new Log();
        //操作说明
        log.setOperatetype(operateType);
        //设置操作人,从session中获取
        log.setOperateor("kyrie");
        String ip = HttpContextUtil.getIpAddress();
        log.setIp(ip);
        Object result = null;
        //让代理方法执行
        //2.相当于后置通知(方法成功执行后走这里)
        try {
            result = pjp.proceed();
            log.setOperateresult("正常");
        } catch (Throwable throwable) {
            //3.相当于异常通知部分
            log.setOperateresult("失败");
        } finally {
            //4.相当于最终通知
            log.setOperatedate(new Date());
            logService.insert(log);
        }
        return result;
    }
}
