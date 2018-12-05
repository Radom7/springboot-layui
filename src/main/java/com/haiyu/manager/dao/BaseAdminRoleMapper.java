package com.haiyu.manager.dao;

import com.haiyu.manager.pojo.BaseAdminRole;
import tk.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseAdminRoleMapper extends MyMapper<BaseAdminRole> {

    List<BaseAdminRole> getRoleList();

    List<BaseAdminRole> getRoles();

    int updateRole(BaseAdminRole role);

    int updateRoleStatus(@Param("id") Integer id,@Param("roleStatus") Integer roleStatus);

}