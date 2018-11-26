package com.wecat.small.mapper;

import com.wecat.small.entity.NationEntity;

public interface NationEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NationEntity record);

    int insertSelective(NationEntity record);

    NationEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NationEntity record);

    int updateByPrimaryKey(NationEntity record);
}