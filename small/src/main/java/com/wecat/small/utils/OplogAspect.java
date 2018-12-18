package com.wecat.small.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import com.wecat.small.common.SqlType;
import com.wecat.small.common.SystemControllerLog;
import com.wecat.small.entity.Hr;
import com.wecat.small.entity.Oplog;
import com.wecat.small.service.OplogService;


@Aspect //AOP 切面
@Component
public class OplogAspect {
	
	@Autowired OplogService oplogService;
	
	
	
	Integer id;
	Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String nowDate=sdf.format(date);
    
    private String title="info";
    private String exception="正常";
    
	//切入点
	@Pointcut("execution(* com.wecat.small.Controller..*.*(..)) "
			+ "and !execution(* com.wecat.small.service.MenuService.getAllMenu()) "
			+ "and !execution(* com.wecat.small.Controller.OplogController.addItem(..)) "
			+ "and !execution(* com.wecat.small.Controller.OplogController.update(..)) "
			+ "and !execution(* com.wecat.small.Controller.OplogController.list(..)) "
			+ "and !execution(* com.wecat.small.Controller.ConfigController.sysmenu())")
    private void pointcut() {
          
    }


    /**
     * 在方法执行前
     *
     * @param point
     * @param myLog
     * @return
     */
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) {
        System.out.println("开始执行该方法:"+nowDate);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getRequestURI().equals("/config/sysmenu")||request.getRequestURI().equals("/Oplog/list")){
        }else{
        	Oplog oplog=new Oplog();
            oplog.setCreateDate(new Date());
            oplogService.insert(oplog);
            id=oplog.getId();
        }
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
    	Map<String, String> map=getControllerMethodDescription(joinPoint);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getRequestURI().equals("/config/sysmenu")||request.getRequestURI().equals("/Oplog/list")){
        	return;
        }
        Hr hr=(Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId=hr.getId();
        Oplog oplog=new Oplog();
        oplog.setId(id);
        oplog.setHrid(userId.intValue());
        oplog.setRequestUri(request.getRequestURL().toString());
        oplog.setRemoteAddr(request.getRequestURI());
        oplog.setType(map.get("type"));
        oplog.setTitle(title);
        oplog.setOperate(map.get("discription"));
        oplog.setMethod(request.getMethod());
        oplog.setEndTime(new Date());
        oplog.setException(exception);
        oplog.setParams(this.getParams(request,request.getParameterNames()));
        oplogService.update(oplog);
        System.out.println("方法已完成执行:"+nowDate);
        System.out.println("参数:"+request.getParameterNames().toString());
        System.out.println("参数:"+request.getParameterMap());
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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getRequestURI().equals("/config/sysmenu")||request.getRequestURI().equals("/Oplog/list")){
         	return;
        }
    	title="error"; 
    	exception="异常";
    	Map<String, String> map=getControllerMethodDescription(joinPoint);
        Hr hr=(Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId=hr.getId();
        Oplog oplog=new Oplog();
        oplog.setId(id);
        oplog.setHrid(userId.intValue());
        oplog.setRequestUri(request.getRequestURL().toString());
        oplog.setRemoteAddr(request.getRequestURI());
        oplog.setType(map.get("type"));
        oplog.setTitle(title);
        oplog.setOperate(map.get("discription"));
        oplog.setMethod(request.getMethod());
        oplog.setEndTime(new Date());
        oplog.setException(exception);
        oplog.setParams(this.getParams(request,request.getParameterNames()));
        oplogService.update(oplog);
        System.out.println("请求出现异常");
    }
    
    
    
    
    
     /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * 
     * @param joinPoint 切点
     * @return discription
     */
     public static Map<String, String> getControllerMethodDescription(JoinPoint joinPoint){
 		Map<String, String> map=new HashMap<>();
    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SystemControllerLog controllerLog = method.getAnnotation(SystemControllerLog.class);
		if(controllerLog==null){
            map.put("discription", null);
			map.put("type", null);
			return map;
		}
		String discription = controllerLog.description(); 
		SqlType sqlType=controllerLog.type();
		String  type=sqlType.getName();
		map.put("discription", discription);
		map.put("type", type);
		return map;
	 }
     
     @SuppressWarnings("rawtypes")
     public String getParams(HttpServletRequest request,Enumeration<String> enumeration){
            String parm=null;
    		String val="";
    		while(enumeration.hasMoreElements()){
    			//参数名
    			parm=enumeration.nextElement();
    			//值
    			val+=request.getParameter(parm);
    		}
    	    return val;
     }

}
