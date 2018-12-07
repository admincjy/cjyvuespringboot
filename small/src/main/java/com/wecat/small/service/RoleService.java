package com.wecat.small.service;

import com.wecat.small.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.wecat.small.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wecat.small.common.BaseRespData;
import com.wecat.small.common.PageInfoReqVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cjy
 * @since 2018-12-06
 */
@Service
@Transactional
public class RoleService{

    @Autowired
    private RoleMapper targetMapper;
    
    /**
     * 获取分页数据列表
     */
    public BaseRespData selectByPage(PageInfoReqVo<Role> pageInfoReqVo){
		PageHelper.startPage(pageInfoReqVo.getPage(), pageInfoReqVo.getSize());
		List<Role> eList=targetMapper.selectByPage(pageInfoReqVo);
		PageInfo<Role> pageDataList = new PageInfo<>(eList);
		BaseRespData baseRespData=new BaseRespData();
		baseRespData.setAaData(pageDataList.getList());
		baseRespData.setStaus(0);
		baseRespData.setDataCount(pageDataList.getTotal());
    	return baseRespData;
    }
    
    
    /**
     * 获取全部数据
     */
    public List<Role> selectList(){
        List<Role> entitys = targetMapper.selectList();
        return entitys;
    }


    /**
     * 根据ID查找数据
     */
    public Role selectById(Long id){
        Role entity = targetMapper.selectById(id);
        return entity;
    }


    /**
     * 添加数据
     */
    public int insert(Role entity){
        int isOk = targetMapper.insert(entity);
        return isOk;
    }


    /**
     * 更新数据
     */
    public int update(Role entity){
        int isOk = targetMapper.update(entity);
        return isOk;
     }
    
    /**
     * 删除数据
     */
    public int deleteById(Long id){
        int isOk = targetMapper.deleteById(id);
        return isOk;
    }

    /**
     * 批量删除数据
     */
    public int deleteBatchIds(List<Long> ids){
        int isOk = targetMapper.deleteBatchIds(ids);
        return isOk;
    }
    
    

}

