package com.wecat.small.mapper;

import com.wecat.small.entity.OplogEntity;

public interface OplogEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OplogEntity record);

    int insertSelective(OplogEntity record);

    OplogEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OplogEntity record);

    int updateByPrimaryKey(OplogEntity record);
}