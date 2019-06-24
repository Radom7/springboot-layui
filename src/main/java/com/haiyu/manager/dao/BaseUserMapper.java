package com.haiyu.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haiyu.manager.dto.BaseUser;
import com.haiyu.manager.response.PageDataResult;

public interface BaseUserMapper {

	PageDataResult getBaseUserList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

	List<BaseUser> getBaseUsers(BaseUser baseUser);

	BaseUser findBaseUserById(@Param("id") Integer id);

	int updateBaseUser(BaseUser role);

	int delBaseUser(@Param("id") Integer id, @Param("status") Integer status);

	int recoverBaseUser(@Param("id") Integer id, @Param("status") Integer status);

	int addBaseUser(BaseUser role);

}
