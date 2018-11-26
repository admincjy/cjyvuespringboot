package com.wecat.small.mapper;


import java.util.List;


import com.wecat.small.entity.PositionEntity;

public interface PositionEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PositionEntity record);

    int insertSelective(PositionEntity record);

    PositionEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PositionEntity record);

    int updateByPrimaryKey(PositionEntity record);
    
    List<PositionEntity> selectAll();
}