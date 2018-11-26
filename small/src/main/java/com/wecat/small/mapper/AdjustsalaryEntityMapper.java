package com.wecat.small.mapper;

import com.wecat.small.entity.AdjustsalaryEntity;

public interface AdjustsalaryEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdjustsalaryEntity record);

    int insertSelective(AdjustsalaryEntity record);

    AdjustsalaryEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdjustsalaryEntity record);

    int updateByPrimaryKey(AdjustsalaryEntity record);
}