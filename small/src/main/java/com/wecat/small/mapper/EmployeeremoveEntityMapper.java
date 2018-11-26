package com.wecat.small.mapper;

import com.wecat.small.entity.EmployeeremoveEntity;

public interface EmployeeremoveEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeremoveEntity record);

    int insertSelective(EmployeeremoveEntity record);

    EmployeeremoveEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeeremoveEntity record);

    int updateByPrimaryKey(EmployeeremoveEntity record);
}