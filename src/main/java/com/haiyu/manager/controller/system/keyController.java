package com.haiyu.manager.controller.system;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.haiyu.manager.common.utils.AuthorizationUtils;
import com.haiyu.manager.dto.BaseUser;
import com.haiyu.manager.service.BaseUserService;

@Controller
@RequestMapping("key")
public class keyController {

	@Autowired
	BaseUserService baseUserService;

	/**
	 * 获取注册码
	 * 
	 * @return
	 */
	@RequestMapping("/nextKey")
	@ResponseBody
	public Map<String, Object> toPage(@RequestParam("phone") String phone) {
		Map<String, Object> retMap = new HashMap<>();
		
		String salt = AuthorizationUtils.getSalt();
		String regCode = AuthorizationUtils.getRegisterCode(phone, salt);

		BaseUser baseUser = new BaseUser();
		baseUser.setSalt(salt);
		baseUser.setPhone(phone);
		baseUser.setActivateFlag("0");
		baseUser.setRegisterCode(regCode);
		baseUser.setRegisterDate(new Timestamp(new Date().getTime()));
		retMap = baseUserService.addBaseUser(baseUser);
		retMap.put("regCode", regCode);
		return retMap;
	}

	/**
	 * 激活注册码
	 * 
	 * @return
	 */
	@RequestMapping("/useKey")
	@ResponseBody
	public Map<String, Object> useKey(HttpServletRequest request) {

		Map<String, Object> retMap = new HashMap<>();
		String phone = request.getParameter("phone");
		String regCode = request.getParameter("regCode");
		BaseUser baseUser = new BaseUser();
		baseUser.setPhone(phone);
		baseUser.setRegisterCode(regCode);
		List<BaseUser> baseUsers = baseUserService.getBaseUsers(baseUser);
		if (CollectionUtils.isEmpty(baseUsers) || baseUsers.size() > 1) {
			retMap.put("code", 2);
			retMap.put("msg", "验证失败");
			return retMap;
		}

		String machineCode = AuthorizationUtils.getMachineCode();
		baseUser = baseUsers.get(0);
		baseUser.setActivateFlag("1");
		baseUser.setActivateDate(new Timestamp(new Date().getTime()));
		baseUser.setMachineCode(machineCode);
		baseUser.setAuthCode(AuthorizationUtils.getAuthCode(machineCode, baseUser.getSalt()));

		return baseUserService.updateBaseUser(baseUser);

	}

	/**
	 * 云检测注册码
	 * 
	 * @return
	 */
	@RequestMapping("/yunCheck")
	@ResponseBody
	public Map<String, Object> yunCheck(HttpServletRequest request) {

		Map<String, Object> retMap = new HashMap<>();
		BaseUser qbaseUser = new BaseUser();

		String machineCode = request.getParameter("machineCode");
		qbaseUser.setMachineCode(machineCode);
		List<BaseUser> baseUsers = baseUserService.getBaseUsers(qbaseUser);
		if (CollectionUtils.isEmpty(baseUsers)) {
			retMap.put("code", 2);
			retMap.put("msg", "验证失败");
			return retMap;
		}

		BaseUser baseUser = baseUsers.get(0);
		String authCode = AuthorizationUtils.getAuthCode(machineCode, baseUser.getSalt());
		if (authCode.equals(baseUser.getAuthCode())) {
			baseUser.setYunSuccess(baseUser.getYunSuccess() + 1);
			baseUser.setYunSuccessDate(new Timestamp(new Date().getTime()));
		} else {
			baseUser.setYunFail(baseUser.getYunFail() + 1);
			baseUser.setYunFailDate(new Timestamp(new Date().getTime()));
		}

		return baseUserService.updateBaseUser(baseUser);
	}

	
	/**
	 * 展示列表数据
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<>();
		BaseUser baseUser = new BaseUser();
		List<BaseUser> userList = baseUserService.getBaseUsers(baseUser);
		ret.put("code", 200);
		ret.put("list", userList);
		ret.put("totals", userList.size());
		return ret;
	}
}
