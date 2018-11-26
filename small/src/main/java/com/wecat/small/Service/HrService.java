/**
 * 
 */
package com.wecat.small.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wecat.small.entity.Hr;
import com.wecat.small.mapper.HrMapper;
import com.wecat.small.utils.BaseRespMsg;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class HrService implements UserDetailsService{
     
	@Autowired
	HrMapper hrMapper;
	
	
	/* （非 Javadoc）
	 * 实现登陆操作
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Hr hr=hrMapper.loadUserByUsername(username);
		if (hr==null) {
			throw new UsernameNotFoundException("用户名错误");
		}
		return hr;
	}
	
	public BaseRespMsg hrReg(String username, String password) {
	    //如果用户名存在，返回错误
	    if (hrMapper.loadUserByUsername(username) != null) {
	        return new BaseRespMsg(2, "用户名已存在,请重新注册");
	    }
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String encode = encoder.encode(password);
	    int count=hrMapper.insterHrReg(username, encode);
	    if (count!=1) {
	    	return new BaseRespMsg(2, "未知错误,请重新注册");
		} else {
			return new BaseRespMsg(1, "注册成功");
		}
	}

}
