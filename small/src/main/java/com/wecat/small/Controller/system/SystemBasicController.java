/**
 * 
 */
package com.wecat.small.Controller.system;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wecat.small.common.BaseRespMsg;
import com.wecat.small.entity.Department;
import com.wecat.small.entity.JoblevelEntity;
import com.wecat.small.entity.PositionEntity;
import com.wecat.small.service.DepartmentService;
import com.wecat.small.service.JoblevelsService;
import com.wecat.small.service.PositionsService;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/system/basic")
public class SystemBasicController {
	
    @Autowired DepartmentService departmentService;
    @Autowired PositionsService positionsService;
    @Autowired JoblevelsService joblevelsService;
	
	@RequestMapping(value = "/dep/{pid}", method = RequestMethod.GET)
	public List<Department> getDepByPid(@PathVariable("pid") Long pid){
		return departmentService.getDepByPid(pid);
	}
	
	/*
	 * 添加部门，使用存储过程操作
	 * 前端传父类id和部门名称   在service层处理部分逻辑   该存储过程没有返回值故mapper层没有返回值用viod 
	 * (涉及查询上一次新增得到的id最好用存储过程操作)  
	 */
	@RequestMapping("/dep")
	public BaseRespMsg addDep(Department department){
		if (departmentService.addDep(department)==1) {
			return new BaseRespMsg(200,"添加成功");
		}
		new BaseRespMsg();
		return BaseRespMsg.error();
	}
	
	/*
	 * 删除部门，使用存储过程操作
	 * 前端传部门id   在service层处理部分逻辑   该存储过程没有返回值故mapper层没有返回值用viod 
	 */
	@RequestMapping("/dep/{did}")
	public BaseRespMsg deleteDep(@PathVariable Integer  did){
		if (departmentService.deleteDep(did)==1) {
			return new BaseRespMsg(200,"删除成功");
		}
		new BaseRespMsg();
		return BaseRespMsg.error();
	}
	
	/*
	 * 职位列表
	 */
	@RequestMapping("/positions")
	public List<PositionEntity> positions(){
		
		return positionsService.selectAll();
	}
	
	/*
	 * 职称列表
	 */
	@RequestMapping("/joblevels")
	public List<JoblevelEntity> joblevels(){
		List<JoblevelEntity> joblevelEntitys=joblevelsService.selectAll();
		for(JoblevelEntity entity:joblevelEntitys){	
			entity.setTitleLevel(entity.getTitlelevel());
			entity.setCreateDate(entity.getCreatedate());
		}
		return joblevelEntitys;
	}

}
