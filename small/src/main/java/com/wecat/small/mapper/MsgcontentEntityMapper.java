package com.wecat.small.mapper;

import com.wecat.small.entity.MsgcontentEntity;

public interface MsgcontentEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgcontentEntity record);

    int insertSelective(MsgcontentEntity record);

    MsgcontentEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgcontentEntity record);

    int updateByPrimaryKey(MsgcontentEntity record);
}