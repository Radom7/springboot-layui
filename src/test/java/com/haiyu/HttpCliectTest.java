package com.haiyu;

import org.json.JSONObject;
import org.junit.Test;

import com.haiyu.manager.common.utils.HttpClientUtil;

public class HttpCliectTest {

	@Test
	public void aa() throws Exception {
		JSONObject paramjson = new JSONObject();
		paramjson.put("input_data", "'ZZS0420190425011','刘金荣','410104196205305524'");
		//String s =HttpClientUtil.httpPost("http://10.96.2.139:5000/test", paramjson.toString());
		String s =HttpClientUtil.httpGet("http://10.96.2.139:5000/test", null);
		System.out.println(s);
	}
}
