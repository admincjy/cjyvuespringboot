/**
 * 
 */
package com.wecat.small.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wecat.small.entity.JoblevelEntity;
import com.wecat.small.mapper.JoblevelEntityMapper;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class JoblevelsService {
	
   @Autowired JoblevelEntityMapper joblevelEntityMapper;
   
   public List<JoblevelEntity> selectAll(){
	   return joblevelEntityMapper.selectAll();
   }
   
}
