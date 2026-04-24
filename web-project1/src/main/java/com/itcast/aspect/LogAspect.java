package com.itcast.aspect;

import com.itcast.mapper.OperateLogMapper;
import com.itcast.pojo.OperateLog;
import com.itcast.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Description LogAspect
 * @Author kight-tom
 * @Date 2026-04-15  10:31
 */
@Aspect
@Slf4j
@Component
@RequiredArgsConstructor //lombok提供构造器注入注解 ，要求注入的成员使用final修饰，会自动根据参数类型进行注入
public class LogAspect {

    private final OperateLogMapper operateLogMapper; //注入操作日志Mapper

/*    @Around("execution(* com.itcast.webproject1.controller.*.insert*(..)) || "  +
             "execution(* com.itcast.webproject1.controller.*.update*(..)) ||"  +
             "execution(* com.itcast.webproject1.controller.*.delete*(..)) ||" +
             "@annotation(com.itcast.webproject1.anno.LogOperator)")*/
    @Around("execution(* com.itcast.controller.*.insert*(..)) || "  +
             "execution(* com.itcast.controller.*.update*(..)) ||"  +
             "execution(* com.itcast.controller.*.delete*(..)) ||" +
            "execution(* com.itcast.controller.*.violation*(..)) ||" +
             "@annotation(com.itcast.anno.LogOperator)")
    //@Around("execution(* com.itcast.webproject1.controller.*.insert*(..))")
    //@Around("@annotation(com.itcast.webproject1.anno.LogOperator)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("日志切面开始执行");

        //1.实例化日志对象
        OperateLog operateLog = new OperateLog();

        //2.封装数据
        //获取当前登录员工ID
        operateLog.setOperateEmpId(getCurrentLoginEmpId());
        //获取系统当前时间
        operateLog.setOperateTime(LocalDateTime.now());
        //获取类名
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        //获取方法名
        operateLog.setMethodName(joinPoint.getSignature().getName());
        //获取方法参数
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        //执行开始 时间
        long begin = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.proceed();
        //获取返回值并限制长度，防止数据截断异常
        //operateLog.setReturnValue(result == null ? "" : result.toString());
        String returnValueStr = (result == null ? "" : result.toString());
        if (returnValueStr.length() > 500) {
            returnValueStr = returnValueStr.substring(0, 500) + "...";
        }
        operateLog.setReturnValue(returnValueStr);
        //执行结束 时间
        long end = System.currentTimeMillis();
        //计算耗时
        operateLog.setCostTime(end - begin);
        //3.保存日志
        operateLogMapper.operateInsert(operateLog);
        log.info("日志切面结束执行");
        return result;
    }

    //获取当前登录员工ID
    private Integer getCurrentLoginEmpId() {

        //方案1：使用request读取请求头中的token，从载荷中解析出员工ID（不好，切面方法运行一次解析一次token，存在大量重复解析，性能不好）

        //方案2：使用ThreadLocal保存当前登录员工ID
        return ThreadLocalUtil.getLoginId();
    }
}
