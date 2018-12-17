package com.wecat.small.Controller.system;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.wecat.small.service.HrRoleService;
import com.wecat.small.service.HrService;
import com.wecat.small.entity.Hr;
import com.wecat.small.entity.hrRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.wecat.small.common.BaseRespData;
import com.wecat.small.common.BaseRespMsg;
import com.wecat.small.common.PageInfoReqVo;
import com.wecat.small.common.SqlType;
import com.wecat.small.common.SystemControllerLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cjy
 * @since 2018-12-06
 */
@RestController
@RequestMapping("Hr")
public class HrController {


    @Autowired
    private HrService targetService;
    @Autowired
    private HrRoleService hrRoleService;

    
    /**
     * 获取分页数据列表
     */
    @SystemControllerLog(description="分页查询用户",type=SqlType.SELECT)
    @RequestMapping("/list")
	public BaseRespData list(@RequestBody PageInfoReqVo<Hr> pageInfoReqVo){
    	BaseRespData baseRespData=targetService.selectByPage(pageInfoReqVo);
		return baseRespData;
	}


    /**
     * 获取全部数据
     */
    @SystemControllerLog(description="查询所有用户信息",type=SqlType.SELECT)
    @RequestMapping("/all")
    public BaseRespData findAll(){
        List<Hr> entitys = targetService.selectList();
        BaseRespData baseRespData=new BaseRespData();
        baseRespData.setAaData(entitys);
        baseRespData.setStaus(0);
        return baseRespData;
    }


    /**
     * 根据ID查找数据
     */
    @SystemControllerLog(description="查询",type=SqlType.SELECT)
    @RequestMapping("/find")
    public BaseRespData find(@RequestParam("id") Long id){
        Hr entity = targetService.selectById(id);
        BaseRespData baseRespData=new BaseRespData();
        baseRespData.setObData(entity);
        baseRespData.setStaus(0);
        return baseRespData;
    }


    /**
     * 添加数据
     */
    @SystemControllerLog(description="新增",type=SqlType.INSERTE)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseRespMsg addItem(@RequestBody Hr entity){
    	entity.setCreateTime(new Date());
    	entity.setEnabled(true);
    	
    	//如果用户名存在，返回错误
	    if (targetService.loadbyUsername(entity.getUsername()) != null) {
	        return new BaseRespMsg(99, "用户名已存在,请重新注册");
	    }
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String encode = encoder.encode(entity.getPassword());
	    entity.setPassword(encode);
    	int isOk = targetService.insert(entity);
        if(isOk==1){
            return new BaseRespMsg(0,"添加成功");
        }
        return BaseRespMsg.error("添加失败");
    }


    /**
     * 更新数据
     */
    @SystemControllerLog(description="更新",type=SqlType.UPDTAE)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseRespMsg update(@RequestBody Hr entity){
        int isOk = targetService.update(entity);
        if(isOk==1){
            return new BaseRespMsg(0,"更新成功");
        }
        return BaseRespMsg.error("更新失败");
     }


    /**
     * 批量删除数据
     */
    @RequestMapping("/dels")
    public BaseRespMsg deleteItems(@RequestParam("ids") List<Long> ids){
        int isOk = targetService.deleteBatchIds(ids);
        if(isOk>0){
            return new BaseRespMsg(0,"批量删除成功");
        }
        return BaseRespMsg.error("批量删除失败");
    }
    
    /**
     * 删除数据
     */
    @SystemControllerLog(description="删除",type=SqlType.DELETE)
    @RequestMapping("/del")
    public BaseRespMsg deleteItems(Long id){
    	List<hrRole> hrRoles=hrRoleService.selectByHrid(Integer.valueOf(id.toString()));
        if (!hrRoles.isEmpty()) {
        	List<Integer> ids=new ArrayList<>();
        	for(hrRole entity:hrRoles){
        		ids.add(entity.getId());
        	}
        	hrRoleService.deleteBatchIds(ids);
		}
    	int isOk = targetService.deleteById(id);
        if(isOk==1){
            return new BaseRespMsg(0,"删除成功");
        }
        return BaseRespMsg.error("删除失败");
    }
    
    /**
     * 更新用户角色
     */
    @SystemControllerLog(description="更新用户角色",type=SqlType.UPDTAE)
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public BaseRespMsg updateRole(@RequestBody Map<String, Object> map){
    	List<hrRole> hrRoles=hrRoleService.selectByHrid((Integer) map.get("hrId"));
        if(!hrRoles.isEmpty()) {
        	List<Integer> ids=new ArrayList<>();
        	for(hrRole entity:hrRoles){
        		ids.add(entity.getId());
        	}
        	hrRoleService.deleteBatchIds(ids);
		}
        @SuppressWarnings("unchecked")
		List<Integer> rids=(List<Integer>) map.get("rids");
    	List<hrRole> entitys=new ArrayList<>();
    	for(Integer id:rids){
    		hrRole entity=new hrRole();
    		entity.setHrid((Integer) map.get("hrId"));
    		entity.setRid(id);
    		entitys.add(entity);
    	}
    	int isOk=hrRoleService.batchSave(entitys);
    	if(isOk>0){
            return new BaseRespMsg(0,"修改成功");
        }
    	return new BaseRespMsg(99,"修改失败");
    }
    
  }