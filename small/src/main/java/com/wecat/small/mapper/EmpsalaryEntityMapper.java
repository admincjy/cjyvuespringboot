package com.wecat.small.mapper;

import com.wecat.small.entity.EmpsalaryEntity;

public interface EmpsalaryEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmpsalaryEntity record);

    int insertSelective(EmpsalaryEntity record);

    EmpsalaryEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmpsalaryEntity record);

    int updateByPrimaryKey(EmpsalaryEntity record);
}