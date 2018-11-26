/**
 * 
 */
package com.wecat.small.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author Administrator
 * 由于数据库中外键的约束会导致删除失败时，会抛出一个DataIntegrityViolationException异常，我将这种异常捕获后统一处理。
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	/* （非 Javadoc）
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o,
			Exception e) {
		ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        Map<String, String> map = new HashMap<>();
        map.put("status", "error");
        if (e instanceof DataIntegrityViolationException) {
            map.put("msg", "该角色尚有关联的资源或用户，删除失败!");
        }
        mv.addAllObjects(map);
        return mv;
	}
   
}
