package com.wecat.small.mapper;

import com.wecat.small.entity.EmployeeecEntity;

public interface EmployeeecEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeecEntity record);

    int insertSelective(EmployeeecEntity record);

    EmployeeecEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeeecEntity record);

    int updateByPrimaryKey(EmployeeecEntity record);
}