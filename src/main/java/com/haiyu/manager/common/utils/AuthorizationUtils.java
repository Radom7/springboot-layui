package com.haiyu.manager.common.utils;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class AuthorizationUtils {
	
	private static final int SPLITLENGTH = 4;

	public static void main(String args[]) throws Exception {
		String code = getMachineCode();
		String authCode = getAuthCode(code,"2213");
		System.out.println("机器码：" + code);
		System.out.println("注册码：" + authCode);
	}

	public static String getMachineCode() {
		Set<String> result = new HashSet<>();
		result.add(getCpuMachineCode());
		String code = new Md5PasswordEncoder().encodePassword(result.toString(), "1234");
		return getSplitString(code, "-", 4);

	}
	
	/**
	 * 根据机器码和私盐得到授权码
	 * @param machineCode
	 * @return
	 */
	public static String getAuthCode(String machineCode,String salt) {
		String newCode = "(yunshouhuxxx@gmail.com)[" + machineCode.toUpperCase() + "](xxx应用级产品开发平台)";
		String code1 = new Md5PasswordEncoder().encodePassword(newCode, salt).toUpperCase() ;
		return getSplitString(code1);
	}

	private static String getSplitString(String str) {
		return getSplitString(str, "-", SPLITLENGTH);
	}

	private static String getSplitString(String str, String split, int length) {
		int len = str.length();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if (i % length == 0 && i > 0) {
				temp.append(split);
			}
			temp.append(str.charAt(i));
		}
		String[] attrs = temp.toString().split(split);
		StringBuilder finalMachineCode = new StringBuilder();
		for (String attr : attrs) {
			if (attr.length() == length) {
				finalMachineCode.append(attr).append(split);
			}
		}
		return finalMachineCode.toString().substring(0, finalMachineCode.toString().length() - 1);
	}


	/**
	 * 获取CPU机器码
	 * @return
	 */
	@SuppressWarnings("resource")
	private static String getCpuMachineCode() {
		String serial = "";
		try {
			Process process = Runtime.getRuntime().exec(
					new String[]{"wmic", "cpu", "get", "ProcessorId"});
			process.getOutputStream().close();
			Scanner sc = new Scanner(process.getInputStream());
			sc.next();
			serial = sc.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serial;
	}
	
	/**
	 * 随机得到6位数用来作为用户私盐
	 * @return
	 */
	public static String getSalt(){
		Double d = new Double((Math.random()*9+1)*100000);
		int i = d.intValue();
		return i+"";
	}
	
	/**
	 * 根据手机号和私盐得到注册码
	 * @param phone
	 * @param salt
	 * @return
	 */
	public static String getRegisterCode(String phone, String salt) {
		String code = new Md5PasswordEncoder().encodePassword(phone, salt).toUpperCase() ;
		return getSplitString(code, "-", 4);
	}

}
