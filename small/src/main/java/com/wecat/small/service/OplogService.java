package com.wecat.small.service;

import com.wecat.small.entity.Oplog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.wecat.small.mapper.OplogMapper;
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
 * @since 2018-12-13
 */
@Service
@Transactional
public class OplogService{

    @Autowired
    private OplogMapper targetMapper;
    
    /**
     * 获取分页数据列表
     */
    public BaseRespData selectByPage(PageInfoReqVo<Oplog> pageInfoReqVo){
		PageHelper.startPage(pageInfoReqVo.getPage(), pageInfoReqVo.getSize());
		List<Oplog> eList=targetMapper.selectByPage(pageInfoReqVo);
		PageInfo<Oplog> pageDataList = new PageInfo<>(eList);
		BaseRespData baseRespData=new BaseRespData();
		baseRespData.setAaData(pageDataList.getList());
		baseRespData.setStaus(0);
		baseRespData.setDataCount(pageDataList.getTotal());
    	return baseRespData;
    }
    
    
    /**
     * 获取全部数据
     */
    public List<Oplog> selectList(){
        List<Oplog> entitys = targetMapper.selectList();
        return entitys;
    }


    /**
     * 根据ID查找数据
     */
    public Oplog selectById(Long id){
        Oplog entity = targetMapper.selectById(id);
        return entity;
    }


    /**
     * 添加数据
     */
    public int insert(Oplog entity){
        int isOk = targetMapper.insert(entity);
        return isOk;
    }


    /**
     * 更新数据
     */
    public int update(Oplog entity){
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

