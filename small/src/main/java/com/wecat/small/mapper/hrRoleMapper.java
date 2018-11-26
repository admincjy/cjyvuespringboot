package com.wecat.small.mapper;

import com.wecat.small.entity.hrRole;

public interface hrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(hrRole record);

    int insertSelective(hrRole record);

    hrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(hrRole record);

    int updateByPrimaryKey(hrRole record);
}