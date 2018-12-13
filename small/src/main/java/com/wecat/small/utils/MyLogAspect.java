package com.wecat.small.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import com.wecat.small.common.MyLog;
import com.wecat.small.entity.Hr;

@Aspect //AOP 切面
@Component
public class MyLogAspect {


    //切入点
    @Pointcut(value = "@annotation(com.wecat.small.common.MyLog)")
    private void pointcut() {

    }


    /**
     * 在方法执行前
     *
     * @param point
     * @param myLog
     * @return
     */
    @Around(value = "pointcut() && @annotation(myLog)")
    public Object around(ProceedingJoinPoint point, MyLog myLog) {

        System.out.println("++++方法执行前前前前前前前：++++");

        String requestUrl = myLog.requestUrl();

        //拦截的类名
        @SuppressWarnings("rawtypes")
		Class clazz = point.getTarget().getClass();
        //拦截的方法
        Method method = ((MethodSignature)point.getSignature()).getMethod();
        Hr userDetails = (Hr) SecurityContextHolder.getContext()
        	    .getAuthentication()
        	    .getPrincipal();
        System.out.println(userDetails.getId()+userDetails.getAddress());
        System.out.println("执行了 类:" + clazz + " 方法:" + method + " 自定义请求地址:" + requestUrl);

        try {
            return point.proceed(); //执行程序
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

    /**
     * 方法执行后
     *
     * @param joinPoint
     * @param myLog
     * @param result
     * @return
     */
    @AfterReturning(value = "pointcut() && @annotation(myLog)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, MyLog myLog, Object result) {

//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();

        System.out.println("++++方法执行后后后后后++++");

        System.out.println("执行结果：" + result);

        return result;
    }

    /**
     * 方法执行后 并抛出异常
     *
     * @param joinPoint
     * @param myLog
     * @param ex
     */
    @AfterThrowing(value = "pointcut() && @annotation(myLog)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, MyLog myLog, Exception ex) {
        System.out.println("++++方法异常异常异常异常异常后++++");
        System.out.println("请求：" + myLog.requestUrl() + " 出现异常");
    }

}
