package com.wecat.small.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wecat.small.entity.hrRole;
import com.wecat.small.mapper.hrRoleMapper;

@Service
@Transactional
public class HrRoleService {
	
	@Autowired hrRoleMapper hrRoleMapper;
	
	public int batchSave(List<hrRole> record){
		 return hrRoleMapper.batchSave(record);
	}
	
	public List<hrRole> selectByHrid(Integer hrid){
		return hrRoleMapper.selectByHrid(hrid);
	}
	
	public int deleteBatchIds(List<Integer> ids){
		return hrRoleMapper.deleteBatchIds(ids);
	}

}
