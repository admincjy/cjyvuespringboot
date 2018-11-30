package com.wecat.small.service;

import com.wecat.small.entity.Msgcontent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.wecat.small.mapper.MsgcontentMapper;
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
public class MsgcontentService{

    @Autowired
    private MsgcontentMapper targetMapper;
    
    /**
     * 获取分页数据列表
     */
    public BaseRespData selectByPage(PageInfoReqVo<Msgcontent> pageInfoReqVo){
		PageHelper.startPage(pageInfoReqVo.getPage(), pageInfoReqVo.getSize());
		List<Msgcontent> eList=targetMapper.selectByPage(pageInfoReqVo);
		PageInfo<Msgcontent> pageDataList = new PageInfo<>(eList);
		BaseRespData baseRespData=new BaseRespData();
		baseRespData.setAaData(eList);
		baseRespData.setStaus(0);
		baseRespData.setDataCount(pageDataList.getTotal());
    	return baseRespData;
    }
    
    
    /**
     * 获取全部数据
     */
    public List<Msgcontent> selectList(){
        List<Msgcontent> entitys = targetMapper.selectList();
        return entitys;
    }


    /**
     * 根据ID查找数据
     */
    public Msgcontent selectById(Long id){
        Msgcontent entity = targetMapper.selectById(id);
        return entity;
    }


    /**
     * 添加数据
     */
    public int insert(Msgcontent entity){
        int isOk = targetMapper.insert(entity);
        return isOk;
    }


    /**
     * 更新数据
     */
    public int update(Msgcontent entity){
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

