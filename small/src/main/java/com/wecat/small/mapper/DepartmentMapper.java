package com.wecat.small.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wecat.small.entity.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    List<Department> getDepByPid(Long pid);
    
    void addDep(@Param("dep") Department department);
    
    void deleteDep(@Param("dep") Department department);
}