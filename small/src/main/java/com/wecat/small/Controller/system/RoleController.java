package com.wecat.small.Controller.system;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.wecat.small.service.RoleService;
import com.wecat.small.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wecat.small.common.BaseRespData;
import com.wecat.small.common.BaseRespMsg;
import com.wecat.small.common.PageInfoReqVo;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cjy
 * @since 2018-12-06
 */
@RestController
@RequestMapping("Role")
public class RoleController {


    @Autowired
    private RoleService targetService;

    
    /**
     * 获取分页数据列表
     */
    @RequestMapping("/list")
	public BaseRespData list(@RequestBody PageInfoReqVo<Role> pageInfoReqVo){
    	BaseRespData baseRespData=targetService.selectByPage(pageInfoReqVo);
		return baseRespData;
	}


    /**
     * 获取全部数据
     */
    @RequestMapping("/all")
    public BaseRespData findAll(){
        List<Role> entitys = targetService.selectList();
        BaseRespData baseRespData=new BaseRespData();
        baseRespData.setAaData(entitys);
        baseRespData.setStaus(0);
        return baseRespData;
    }


    /**
     * 根据ID查找数据
     */
    @RequestMapping("/find")
    public BaseRespData find(@RequestParam("id") Long id){
        Role entity = targetService.selectById(id);
        BaseRespData baseRespData=new BaseRespData();
        baseRespData.setObData(entity);
        baseRespData.setStaus(0);
        return baseRespData;
    }


    /**
     * 添加数据
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseRespMsg addItem(@RequestBody Role entity){
        int isOk = targetService.insert(entity);
        if(isOk==1){
            return new BaseRespMsg(0,"添加成功");
        }
        return BaseRespMsg.error("添加失败");
    }


    /**
     * 更新数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseRespMsg update(@RequestBody Role entity){
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
    @RequestMapping("/del")
    public BaseRespMsg deleteItems(@RequestParam("id") Long id){
        int isOk = targetService.deleteById(id);
        if(isOk==1){
            return new BaseRespMsg(0,"删除成功");
        }
        return BaseRespMsg.error("删除失败");
    }
    
  }