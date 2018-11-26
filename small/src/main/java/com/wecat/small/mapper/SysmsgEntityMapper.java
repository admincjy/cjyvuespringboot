package com.wecat.small.mapper;

import com.wecat.small.entity.SysmsgEntity;

public interface SysmsgEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysmsgEntity record);

    int insertSelective(SysmsgEntity record);

    SysmsgEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysmsgEntity record);

    int updateByPrimaryKey(SysmsgEntity record);
}