/**
 * 
 */
package com.wecat.small.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wecat.small.entity.EmployeeEntity;
import com.wecat.small.entity.EmployeeecEntity;
import com.wecat.small.entity.PageInfoSmall;
import com.wecat.small.mapper.EmployeeEntityMapper;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class EmpBasicService {
	
	@Autowired EmployeeEntityMapper employeeEntityMapper;
	
	
    public Map<String, Object> selectAll(PageInfoSmall<EmployeeEntity> pageInfo){
    	Map<String, Object> map=new HashMap<>();
		PageHelper.startPage(pageInfo.getPage(), pageInfo.getSize());
		List<EmployeeEntity> eList=employeeEntityMapper.selectAll(pageInfo);
		PageInfo<EmployeeEntity> pageDataList = new PageInfo<>(eList);
		map.put("emps", eList);
		map.put("count", pageDataList.getTotal());
    	return map;
    }
    
    public List<EmployeeEntity> selectAllNoPage(PageInfoSmall<EmployeeEntity> pageInfo){
		List<EmployeeEntity> eList=employeeEntityMapper.selectAll(pageInfo);
    	return eList;
    }

    public int insertSelective(EmployeeEntity record){
    	return employeeEntityMapper.insertSelective(record);
    }
	
	

}
