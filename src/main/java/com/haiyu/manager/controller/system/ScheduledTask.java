package com.haiyu.manager.controller.system;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.haiyu.manager.common.utils.AuthorizationUtils;
import com.haiyu.manager.common.utils.DateUtils;
import com.haiyu.manager.dto.BaseUser;
import com.haiyu.manager.service.BaseUserService;

@Component
public class ScheduledTask {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BaseUserService baseUserService;
	
	@Scheduled(cron="0/30 * * * * ?")
	public void reportCurrentTime() {

		String date = DateUtils.getCurrentDate();
		logger.info("执行了定时器：" + date);
		String machineCode = AuthorizationUtils.getMachineCode();

		BaseUser qbaseUser = new BaseUser();
		qbaseUser.setMachineCode(machineCode);
		List<BaseUser> baseUsers = baseUserService.getBaseUsers(qbaseUser);

		BaseUser baseUser = baseUsers.get(0);
		String authCode = AuthorizationUtils.getAuthCode(machineCode, baseUser.getSalt());
		if (authCode.equals(baseUser.getAuthCode())) {
			baseUser.setYunSuccess(baseUser.getYunSuccess() + 1);
			baseUser.setYunSuccessDate(new Timestamp(new Date().getTime()));
			logger.info(baseUser.getPhone() + " success " + date);
		} else {
			baseUser.setYunFail(baseUser.getYunFail() + 1);
			baseUser.setYunFailDate(new Timestamp(new Date().getTime()));
			logger.info(baseUser.getPhone());
			logger.info(baseUser.getPhone() + " fail " + date);
		}

		baseUserService.updateBaseUser(baseUser);

	}
}
