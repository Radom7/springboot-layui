package com.haiyu.manager.dto;

import java.sql.Timestamp;

public class BaseUser {

	private int id;
	private String phone;
	private String salt;
	private String registerCode;
	private String machineCode;
	private String authCode;
	private Timestamp registerDate;
	private String activateFlag;
	private Timestamp activateDate;
	private int yunSuccess;
	private int yunFail;
	private Timestamp yunSuccessDate;
	private Timestamp yunFailDate;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getRegisterCode() {
		return registerCode;
	}
	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public Timestamp getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}
	public Timestamp getActivateDate() {
		return activateDate;
	}
	public void setActivateDate(Timestamp activateDate) {
		this.activateDate = activateDate;
	}
	public Timestamp getYunSuccessDate() {
		return yunSuccessDate;
	}
	public void setYunSuccessDate(Timestamp yunSuccessDate) {
		this.yunSuccessDate = yunSuccessDate;
	}
	public Timestamp getYunFailDate() {
		return yunFailDate;
	}
	public void setYunFailDate(Timestamp yunFailDate) {
		this.yunFailDate = yunFailDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActivateFlag() {
		return activateFlag;
	}
	public void setActivateFlag(String activateFlag) {
		this.activateFlag = activateFlag;
	}
	public int getYunSuccess() {
		return yunSuccess;
	}
	public void setYunSuccess(int yunSuccess) {
		this.yunSuccess = yunSuccess;
	}
	public int getYunFail() {
		return yunFail;
	}
	public void setYunFail(int yunFail) {
		this.yunFail = yunFail;
	}
	
}
