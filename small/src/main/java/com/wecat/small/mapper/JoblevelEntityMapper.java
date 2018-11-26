package com.wecat.small.mapper;

import java.util.List;

import com.wecat.small.entity.JoblevelEntity;

public interface JoblevelEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JoblevelEntity record);

    int insertSelective(JoblevelEntity record);

    JoblevelEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JoblevelEntity record);

    int updateByPrimaryKey(JoblevelEntity record);
    
    List<JoblevelEntity> selectAll();
}