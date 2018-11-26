package com.wecat.small.mapper;

import com.wecat.small.entity.SalaryEntity;

public interface SalaryEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalaryEntity record);

    int insertSelective(SalaryEntity record);

    SalaryEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalaryEntity record);

    int updateByPrimaryKey(SalaryEntity record);
}