package com.wecat.small.mapper;

import com.wecat.small.entity.Oplog;
import com.wecat.small.common.PageInfoReqVo;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cjy
 * @since 2018-12-13
 */
public interface OplogMapper{

       List<Oplog> selectList();
       
       Oplog selectById(Long id);
       
       int insert(Oplog entity);
       
       int update(Oplog entity);
       
       int deleteById(Long id);
       
       int deleteBatchIds(List<Long> ids);
       
       List<Oplog> selectByPage(PageInfoReqVo<Oplog> pageInfoReqVo);

}
