/**
 * 
 */
package com.wecat.small.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecat.small.entity.MenuEntity;
import com.wecat.small.mapper.MenuEntityMapper;
import com.wecat.small.utils.HrUtils;

/**
 * @author Administrator
 *
 */
@Service
public class MenuService {
	
      @Autowired MenuEntityMapper menuEntityMapper;
      
      public List<MenuEntity> getAllMenu(){
    	  List<MenuEntity> allMenu= menuEntityMapper.getAllMenu();
    	  return allMenu;
      };
      
      
      public List<MenuEntity> getMenusByHrId() {
          return menuEntityMapper.getMenusByHrId(HrUtils.getCurrentHr().getId());
      }
      
      
}
