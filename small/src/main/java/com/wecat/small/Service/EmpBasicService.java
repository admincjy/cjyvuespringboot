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
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wecat.small.entity.Department;
import com.wecat.small.entity.EmployeeEntity;
import com.wecat.small.entity.EmployeeecEntity;
import com.wecat.small.entity.JoblevelEntity;
import com.wecat.small.entity.NationEntity;
import com.wecat.small.entity.PageInfoSmall;
import com.wecat.small.entity.PoliticsstatusEntity;
import com.wecat.small.entity.PositionEntity;
import com.wecat.small.mapper.DepartmentMapper;
import com.wecat.small.mapper.EmployeeEntityMapper;
import com.wecat.small.mapper.JoblevelEntityMapper;
import com.wecat.small.mapper.NationEntityMapper;
import com.wecat.small.mapper.PoliticsstatusEntityMapper;
import com.wecat.small.mapper.PositionEntityMapper;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class EmpBasicService {
	
	@Autowired EmployeeEntityMapper employeeEntityMapper;
	@Autowired NationEntityMapper nationEntityMapper;
	@Autowired PositionEntityMapper positionEntityMapper;
	@Autowired PoliticsstatusEntityMapper politicsstatusEntityMapper;
	@Autowired DepartmentMapper departmentMapper;
	@Autowired JoblevelEntityMapper joblevelEntityMapper;
	
	
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
	
    public Map<String, Object> selectBasicdata(){
    	Map<String, Object> map=new HashMap<>();
    	List<NationEntity> nationEntities=nationEntityMapper.findAll();
    	List<PositionEntity> positionEntities=positionEntityMapper.selectAll();
    	List<PoliticsstatusEntity> politicsstatusEntities=politicsstatusEntityMapper.findAll();
    	List<Department> departments=departmentMapper.findAll();
    	List<JoblevelEntity> joblevelEntities=joblevelEntityMapper.selectAll();
    	map.put("nations", nationEntities);
    	map.put("positions", positionEntities);
    	map.put("politics", politicsstatusEntities);
    	map.put("deps", departments);
    	map.put("joblevels", joblevelEntities);
		return map;
	}
    
    public EmployeeEntity findRecentlyOne(){
    	return employeeEntityMapper.findRecentlyOne();
    }
}
