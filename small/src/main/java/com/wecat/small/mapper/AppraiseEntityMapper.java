package com.wecat.small.mapper;

import com.wecat.small.entity.AppraiseEntity;

public interface AppraiseEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppraiseEntity record);

    int insertSelective(AppraiseEntity record);

    AppraiseEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppraiseEntity record);

    int updateByPrimaryKey(AppraiseEntity record);
}