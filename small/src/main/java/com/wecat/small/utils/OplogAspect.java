package com.wecat.small.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wecat.small.common.MyLog;
import com.wecat.small.common.SystemControllerLog;
import com.wecat.small.entity.Hr;
import com.wecat.small.entity.Oplog;
import com.wecat.small.service.OplogService;


@Aspect //AOP 切面
@Component
public class OplogAspect {
	
	@Autowired OplogService oplogService;
	
	
	Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String nowDate=sdf.format(date);
	
	//切入点
	@Pointcut("execution(* com.wecat.small.Controller..*.*(..)) and !execution(* com.wecat.small.service.MenuService.getAllMenu())")
    private void pointcut() {
          
    }


    /**
     * 在方法执行前
     *
     * @param point
     * @param myLog
     * @return 
     * @return
     */
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) {
        System.out.println("开始执行该方法:"+nowDate);
//        Oplog oplog=new Oplog();
//        oplog.setCreateDate(new Date());
//        oplogService.insert(oplog);
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
    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Hr hr=(Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId=hr.getId();
        Oplog oplog=new Oplog();
        oplog.setHrid(userId.intValue());
        oplog.setRequestUri(request.getRequestURL().toString());
        oplog.setRemoteAddr(request.getRequestURI());
        oplog.setTitle("66");
        oplog.setMethod(request.getMethod());
        System.out.println("request:"+request);
    	System.out.println("joinPoint:"+joinPoint);
        System.out.println("方法已完成执行:"+nowDate);
        System.out.println("执行结果：" + result);
    }

    /**
     * 方法执行后 并抛出异常
     *
     * @param joinPoint
     * @param myLog
     * @param ex
     */
    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("++++方法异常异常异常异常异常后++++");
        System.out.println("请求出现异常");
    }
    
    
     /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * 
     * @param joinPoint 切点
     * @return discription
     */
     public static String getControllerMethodDescription(JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
//		RequestMapping controllerLog = method.getAnnotation(RequestMapping.class);
		String discription = method.getName();
		return discription;
	 }
    

}
