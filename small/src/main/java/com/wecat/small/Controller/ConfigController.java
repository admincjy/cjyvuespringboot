/**
 * 
 */
package com.wecat.small.Controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecat.small.entity.MenuEntity;
import com.wecat.small.service.MenuService;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
	
	@Autowired MenuService menuService;
	
	
	
    @RequestMapping("/sysmenu")
    public List<MenuEntity> sysmenu() {
        return menuService.getMenusByHrId();
    }

}
