/**
 * 
 */
package com.wecat.small.Controller.emp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wecat.small.Service.EmpBasicService;
import com.wecat.small.entity.EmployeeEntity;
import com.wecat.small.entity.EmployeeecEntity;
import com.wecat.small.entity.PageInfoSmall;
import com.wecat.small.mapper.EmployeeecEntityMapper;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
     
	@Autowired EmpBasicService empBasicService;
	
	
	/*
	 * 分页查询员工基本数据
	 * 使用PageHelper插件分页  逻辑处理在service层
	 */
	@RequestMapping(value = "/emp")
	public Map<String, Object> emp(@RequestBody PageInfoSmall<EmployeeEntity> pageInfo){
		Map<String, Object> map=empBasicService.selectAll(pageInfo);
		return map;
	}
	
}
