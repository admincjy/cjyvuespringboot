package com.wecat.small.mapper;

import java.util.List;

import com.wecat.small.common.PageInfoReqVo;
import com.wecat.small.entity.Role;

public interface RoleMapper {
	
    List<Role> selectList();
    
    Role selectById(Long id);
    
    int insert(Role entity);
    
    int update(Role entity);
    
    int deleteById(Long id);
    
    int deleteBatchIds(List<Long> ids);
    
    List<Role> selectByPage(PageInfoReqVo<Role> pageInfoReqVo);
}