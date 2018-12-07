/**
 * 
 */
package com.wecat.small.Controller.emp;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.wecat.small.common.BaseRespMsg;
import com.wecat.small.common.MyLog;
import com.wecat.small.entity.EmailEntity;
import com.wecat.small.entity.EmployeeEntity;
import com.wecat.small.entity.PageInfoSmall;
import com.wecat.small.service.EmpBasicService;
import com.wecat.small.utils.EmailUtils;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
     
	@Autowired EmpBasicService empBasicService;
	
	@Autowired EmailUtils emailUtils;
	
	
	/*
	 * 分页查询员工基本数据
	 * 使用PageHelper插件分页  逻辑处理在service层
	 */
	@MyLog(requestUrl = "/emp请求")
	@RequestMapping(value = "/emp")
	public Map<String, Object> emp(@RequestBody PageInfoSmall<EmployeeEntity> pageInfo){
		Map<String, Object> map=empBasicService.selectAll(pageInfo);
		return map;
	}
	
	
	/*
	 * 查询员工信息中下拉列表的基本信息
	 */
	@RequestMapping(value = "/basicdata")
	public Map<String, Object> basicdata(){
		Map<String, Object> map=empBasicService.selectBasicdata();
		return map;
	}
	
	
	/*
	 * 编辑员工工号
	 */
	@RequestMapping(value = "/maxWorkID")
	public String maxWorkID(){
		String workID=empBasicService.findRecentlyOne().getWorkID();
		workID=String.format("%08d", Integer.parseInt(workID)+1);
		return workID;
	}
	
	/*
	 *  新添加员工信息
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BaseRespMsg add(EmployeeEntity employeeEntity){ 
		employeeEntity.setWorkState("在职");
		employeeEntity.setWorkAge(5);
        int i=empBasicService.insertSelective(employeeEntity);
        if (i==1) {
			return new BaseRespMsg(0,"添加成功");
		}
        return BaseRespMsg.error("添加失败");
	}
	
	/*
	 *  发送邮件
	 */
	@RequestMapping(value = "/sentMail", method = RequestMethod.POST)
	public BaseRespMsg sentMail(EmailEntity emailEntity,BindingResult bindingResult){
		boolean flag = emailUtils.sendEmail(emailEntity.getSubject(), 
				emailEntity.getToUsers(), emailEntity.getCcUsers(), 
				emailEntity.getContent(), emailEntity.getAttachfiles());
		if (flag) {
			return new BaseRespMsg(0,"发送成功");
		} else {
	        return new BaseRespMsg(99,"发送失败");
        }
	}
	
}
