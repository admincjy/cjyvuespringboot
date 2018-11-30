package com.wecat.small.mapper;

import com.wecat.small.entity.Msgcontent;
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
public interface MsgcontentMapper{

       List<Msgcontent> selectList();
       
       Msgcontent selectById(Long id);
       
       int insert(Msgcontent entity);
       
       int update(Msgcontent entity);
       
       int deleteById(Long id);
       
       int deleteBatchIds(List<Long> ids);
       
       List<Msgcontent> selectByPage(PageInfoReqVo<Msgcontent> pageInfoReqVo);

}
