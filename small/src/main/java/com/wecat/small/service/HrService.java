/**
 * 
 */
package com.wecat.small.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wecat.small.common.BaseRespData;
import com.wecat.small.common.BaseRespMsg;
import com.wecat.small.common.PageInfoReqVo;
import com.wecat.small.entity.Hr;
import com.wecat.small.mapper.HrMapper;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class HrService implements UserDetailsService{
     
	@Autowired
	HrMapper hrMapper;
	
	
	/* （非 Javadoc）
	 * 实现登陆操作
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Hr hr=hrMapper.loadUserByUsername(username);
		if (hr==null) {
			throw new UsernameNotFoundException("用户名错误");
		}
		return hr;
	}
	
	public BaseRespMsg hrReg(String username, String password) {
	    //如果用户名存在，返回错误
	    if (hrMapper.loadUserByUsername(username) != null) {
	        return new BaseRespMsg(2, "用户名已存在,请重新注册");
	    }
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String encode = encoder.encode(password);
	    int count=hrMapper.insterHrReg(username, encode);
	    if (count!=1) {
	    	return new BaseRespMsg(2, "未知错误,请重新注册");
		} else {
			return new BaseRespMsg(1, "注册成功");
		}
	}
	
	public Hr loadbyUsername(String username){
		return hrMapper.loadUserByUsername(username);
	}
	
	/**
     * 获取分页数据列表
     */
    public BaseRespData selectByPage(PageInfoReqVo<Hr> pageInfoReqVo){
		PageHelper.startPage(pageInfoReqVo.getPage(), pageInfoReqVo.getSize());
		List<Hr> eList=hrMapper.selectByPage(pageInfoReqVo);
		PageInfo<Hr> pageDataList = new PageInfo<>(eList);
		BaseRespData baseRespData=new BaseRespData();
		baseRespData.setAaData(pageDataList.getList());
		baseRespData.setStaus(0);
		baseRespData.setDataCount(pageDataList.getTotal());
    	return baseRespData;
    }
    
    
    /**
     * 获取全部数据
     */
    public List<Hr> selectList(){
        List<Hr> entitys = hrMapper.selectList();
        return entitys;
    }


    /**
     * 根据ID查找数据
     */
    public Hr selectById(Long id){
        Hr entity = hrMapper.selectById(id);
        return entity;
    }


    /**
     * 添加数据
     */
    public int insert(Hr entity){
        int isOk = hrMapper.insert(entity);
        return isOk;
    }


    /**
     * 更新数据
     */
    public int update(Hr entity){
        int isOk = hrMapper.update(entity);
        return isOk;
     }
    
    /**
     * 删除数据
     */
    public int deleteById(Long id){
        int isOk = hrMapper.deleteById(id);
        return isOk;
    }

    /**
     * 批量删除数据
     */
    public int deleteBatchIds(List<Long> ids){
        int isOk = hrMapper.deleteBatchIds(ids);
        return isOk;
    }

}
