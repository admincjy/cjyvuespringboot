/**
 * 
 */
package com.wecat.small.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecat.small.entity.RespBean;

/**
 * @author Administrator
 *
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

	/* （非 Javadoc）
	 * 通过自定义AccessDeniedHandler我们可以自定义403响应的内容
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
        RespBean error = RespBean.error("权限不足，请联系管理员!");
        out.write(new ObjectMapper().writeValueAsString(error));
        out.flush();
        out.close();
	}
   
}
