package com.haiyu.manager.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
