package com.haiyu.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haiyu.manager.dao.BaseUserMapper;
import com.haiyu.manager.dto.BaseUser;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.BaseUserService;

@Service
public class BaseUserServiceImpl implements BaseUserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BaseUserMapper baseUserMapper;

	@Override
	public PageDataResult getBaseUserList(Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaseUser> getBaseUsers(BaseUser baseUser) {
		return baseUserMapper.getBaseUsers(baseUser);
	}

	@Override
	public BaseUser findBaseUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateBaseUser(BaseUser baseUser) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int result = baseUserMapper.updateBaseUser(baseUser);
			if (result == 0) {
				retMap.put("code", 0);
				retMap.put("msg", "操作失败");
				logger.error("updateBaseUser失败");
				return retMap;
			}
			retMap.put("code", 1);
			retMap.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateBaseUser异常！", e);
		}
		return retMap;
	}

	@Override
	public Map<String, Object> delBaseUser(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> recoverBaseUser(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> addBaseUser(BaseUser baseUser) {

		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int result = baseUserMapper.addBaseUser(baseUser);
			if (result == 0) {
				retMap.put("code", 0);
				retMap.put("msg", "新增用户失败");
				logger.error("新增用户失败");
				return retMap;
			}
			retMap.put("code", 1);
			retMap.put("msg", "新增用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加用户！异常！", e);
		}
		return retMap;
	}

}
