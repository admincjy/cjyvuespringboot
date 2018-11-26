package com.wecat.small.mapper;

import java.util.List;

import com.wecat.small.entity.PoliticsstatusEntity;

public interface PoliticsstatusEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliticsstatusEntity record);

    int insertSelective(PoliticsstatusEntity record);

    PoliticsstatusEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliticsstatusEntity record);

    int updateByPrimaryKey(PoliticsstatusEntity record);
    
    List<PoliticsstatusEntity> findAll();
}