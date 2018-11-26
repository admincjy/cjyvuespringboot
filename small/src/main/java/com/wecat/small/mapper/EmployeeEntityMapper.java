package com.wecat.small.mapper;

import java.util.List;

import com.wecat.small.entity.EmployeeEntity;
import com.wecat.small.entity.PageInfoSmall;



public interface EmployeeEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeEntity record);

    int insertSelective(EmployeeEntity record);

    EmployeeEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeeEntity record);

    int updateByPrimaryKey(EmployeeEntity record);
    
    List<EmployeeEntity> selectAll(PageInfoSmall<EmployeeEntity> pageInfo);
}