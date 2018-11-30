package com.wecat.small.mapper;

import com.wecat.small.entity.Sysmsg;
import com.wecat.small.common.PageInfoReqVo;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cjy
 * @since 2018-11-30
 */
public interface SysmsgMapper{

       List<Sysmsg> selectList();
       
       Sysmsg selectById(Long id);
       
       int insert(Sysmsg entity);
       
       int update(Sysmsg entity);
       
       int deleteById(Long id);
       
       int deleteBatchIds(List<Long> ids);
       
       List<Sysmsg> selectByPage(PageInfoReqVo<Sysmsg> pageInfoReqVo);

}
