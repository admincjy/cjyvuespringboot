/**
 * 
 */
package com.wecat.small.filter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.wecat.small.entity.MenuEntity;
import com.wecat.small.entity.Role;
import com.wecat.small.service.MenuService;

/**
 * @author Administrator
 * 通过当前的请求地址，获取该地址需要的用户角色
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

	@Autowired MenuService menuService;
	AntPathMatcher antPathMatcher = new AntPathMatcher();
	/* （非 Javadoc）
	 * 
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		//获取请求地址
		String requestUrl=((FilterInvocation)object).getRequestUrl();
//		if ("/login_p".equals(requestUrl)) {
//			return null;
//		}
		List<MenuEntity> allMenu= menuService.getAllMenu();
		for(MenuEntity menu:allMenu){
			if (antPathMatcher.match(menu.getUrl(), requestUrl)&&menu.getRoles().size()>0) {
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                //创建一个角色的集合
                return SecurityConfig.createList(values);
            }
		}
		//没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
	}

	/* （非 Javadoc）
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	/* （非 Javadoc）
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return FilterInvocation.class.isAssignableFrom(aClass);
	}

}
