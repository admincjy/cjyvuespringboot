/**
 * 
 */
package com.wecat.small.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wecat.small.entity.PositionEntity;
import com.wecat.small.mapper.PositionEntityMapper;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PositionsService {
	
	@Autowired PositionEntityMapper positionEntityMapper;
	
	public List<PositionEntity> selectAll(){
		return positionEntityMapper.selectAll();
	}

}
