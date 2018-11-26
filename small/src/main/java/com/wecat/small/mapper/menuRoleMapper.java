package com.wecat.small.mapper;

import com.wecat.small.entity.menuRole;

public interface menuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(menuRole record);

    int insertSelective(menuRole record);

    menuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(menuRole record);

    int updateByPrimaryKey(menuRole record);
}