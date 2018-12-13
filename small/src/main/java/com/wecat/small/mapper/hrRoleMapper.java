package com.wecat.small.mapper;

import java.util.List;

import com.wecat.small.entity.hrRole;

public interface hrRoleMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteBatchIds(List<Integer> ids);

    int insert(hrRole record);

    int insertSelective(hrRole record);

    hrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(hrRole record);

    int updateByPrimaryKey(hrRole record);
    
    List<hrRole> selectByHrid(Integer hrid);
    
    int batchSave(List<hrRole> record);
}