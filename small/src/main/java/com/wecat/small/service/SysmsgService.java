package com.wecat.small.service;

import com.wecat.small.entity.Sysmsg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.wecat.small.mapper.SysmsgMapper;
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
 * @since 2018-11-30
 */
@Service
@Transactional
public class SysmsgService{

    @Autowired
    private SysmsgMapper targetMapper;
    
    /**
     * 获取分页数据列表
     */
    public BaseRespData selectByPage(PageInfoReqVo<Sysmsg> pageInfoReqVo){
		PageHelper.startPage(pageInfoReqVo.getPage(), pageInfoReqVo.getSize());
		List<Sysmsg> eList=targetMapper.selectByPage(pageInfoReqVo);
		PageInfo<Sysmsg> pageDataList = new PageInfo<>(eList);
		BaseRespData baseRespData=new BaseRespData();
		baseRespData.setAaData(eList);
		baseRespData.setStaus(0);
		baseRespData.setDataCount(pageDataList.getTotal());
    	return baseRespData;
    }
    
    
    /**
     * 获取全部数据
     */
    public List<Sysmsg> selectList(){
        List<Sysmsg> entitys = targetMapper.selectList();
        return entitys;
    }


    /**
     * 根据ID查找数据
     */
    public Sysmsg selectById(Long id){
        Sysmsg entity = targetMapper.selectById(id);
        return entity;
    }


    /**
     * 添加数据
     */
    public int insert(Sysmsg entity){
        int isOk = targetMapper.insert(entity);
        return isOk;
    }


    /**
     * 更新数据
     */
    public int update(Sysmsg entity){
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

