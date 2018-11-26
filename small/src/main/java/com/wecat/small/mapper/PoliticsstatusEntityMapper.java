package com.wecat.small.mapper;

import com.wecat.small.entity.PoliticsstatusEntity;

public interface PoliticsstatusEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliticsstatusEntity record);

    int insertSelective(PoliticsstatusEntity record);

    PoliticsstatusEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliticsstatusEntity record);

    int updateByPrimaryKey(PoliticsstatusEntity record);
}