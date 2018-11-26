package com.wecat.small.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wecat.small.entity.MenuEntity;

public interface MenuEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuEntity record);

    int insertSelective(MenuEntity record);

    MenuEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuEntity record);

    int updateByPrimaryKey(MenuEntity record);
    
    List<MenuEntity> getAllMenu();
    
    List<MenuEntity> getMenusByHrId(Long id);
}