package com.haiyu.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.haiyu.manager.common.IStatusMessage;
import com.haiyu.manager.common.IStatusMessage.SystemStatus;
import com.haiyu.manager.common.utils.HttpClientUtil;
import com.haiyu.manager.response.ResponseResult;


/**
 * @Title: LoginController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/20 11:39
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("home")
    public String home(){
        logger.info("定向主页");
        return "home";
    }
    
    @RequestMapping("docum")
    public String docum(){
        return "docum";
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }
    
    @RequestMapping("activation")
    public String activation(){
        return "activation";
    }
    
    @RequestMapping("getData")
    @ResponseBody
    public ResponseResult getData(HttpServletRequest request) throws Exception{
    	String input_data = request.getParameter("input_data");
    	if(StringUtil.isEmpty(input_data) || ",,".equals(input_data)){
    		return new ResponseResult(IStatusMessage.SystemStatus.PARAM_ERROR);
    	}
    	
    	ResponseResult ret = new ResponseResult();
    	String s =HttpClientUtil.httpGet("http://10.11.0.116:5500/law?inputdata="+input_data, null);
    	JSONObject json = JSON.parseObject(s);
    	String code = json.getString("code");
    	ret.setObj(s);
    	ret.setCode(SystemStatus.SUCCESS.getCode());
    	ret.setMessage(SystemStatus.SUCCESS.getMessage());
        return ret;
    }
}
