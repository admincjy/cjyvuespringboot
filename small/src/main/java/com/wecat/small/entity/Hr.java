/**
 * 
 */
package com.wecat.small.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Administrator
 *
 */
public class Hr implements UserDetails{
	
	private Long id;
    private String name;
    private String phone;
    private String telephone;
    private String address;
    private boolean enabled;
    private String username;
    private String password;
    private String remark;
    private List<Role> roles;
    private String userface;
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUserface() {
		return userface;
	}

	public void setUserface(String userface) {
		this.userface = userface;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* （非 Javadoc）
	 * 该方法用来获取当前用户所具有的角色
	 */
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//当前用户的权限集合
		List<GrantedAuthority> authorities = new ArrayList<>();
	    for (Role role : roles) {
	        authorities.add(new SimpleGrantedAuthority(role.getName()));
	    }
	    return authorities;
	}

	/* （非 Javadoc）
	 * 账户是否被禁用
	 */
	@Override
	public boolean isEnabled() {
		// TODO 自动生成的方法存根
		return enabled;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	/* （非 Javadoc）
	 * 账户是否过期
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/* （非 Javadoc）
	 * 账户是否锁定
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO 自动生成的方法存根
		return true;
	}

	/* （非 Javadoc）
	 * 凭证是否过期
	 */
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自动生成的方法存根
		return true;
	}

	
	

}