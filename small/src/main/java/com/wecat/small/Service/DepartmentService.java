/**
 * 
 */
package com.wecat.small.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wecat.small.entity.Department;
import com.wecat.small.mapper.DepartmentMapper;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class DepartmentService {
	
	@Autowired DepartmentMapper departmentMapper;
	
	
	public List<Department> getDepByPid(Long pid){
		return departmentMapper.getDepByPid(pid);
	}
	
	public int addDep(@Param("dep") Department department){
		department.setEnabled(true);
		departmentMapper.addDep(department);
		return department.getResult();
	}
	
	public int deleteDep(Integer  did){
		Department department=new Department();
		department.setId(did);
		departmentMapper.deleteDep(department);
		return department.getResult();
	}

}
