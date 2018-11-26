package com.wecat.small.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wecat.small.entity.Hr;
import com.wecat.small.entity.Role;


public interface HrMapper {
	Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(Long id);

    int insterHrReg(@Param("username") String username, @Param("password") String password);

    List<Hr> getHrsByKeywords(@Param("keywords") String keywords);

    int updateHr(Hr hr);

    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    Hr getHrById(Long hrId);

    int deleteHr(Long hrId);

    List<Hr> getAllHr(@Param("currentId") Long currentId);
}