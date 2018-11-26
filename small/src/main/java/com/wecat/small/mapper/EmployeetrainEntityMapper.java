package com.wecat.small.mapper;

import com.wecat.small.entity.EmployeetrainEntity;

public interface EmployeetrainEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeetrainEntity record);

    int insertSelective(EmployeetrainEntity record);

    EmployeetrainEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeetrainEntity record);

    int updateByPrimaryKey(EmployeetrainEntity record);
}