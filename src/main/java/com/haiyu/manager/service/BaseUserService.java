package com.haiyu.manager.service;

import java.util.List;
import java.util.Map;

import com.haiyu.manager.dto.BaseUser;
import com.haiyu.manager.response.PageDataResult;

public interface BaseUserService {

	PageDataResult getBaseUserList(Integer pageNum, Integer pageSize);

	List<BaseUser> getBaseUsers(BaseUser baseUser);

	BaseUser findBaseUserById(Integer id);

	Map<String, Object> updateBaseUser(BaseUser baseUser);

	Map<String, Object> delBaseUser(Integer id, Integer status);

	Map<String, Object> recoverBaseUser(Integer id, Integer status);

	Map<String, Object> addBaseUser(BaseUser baseUser);

}
