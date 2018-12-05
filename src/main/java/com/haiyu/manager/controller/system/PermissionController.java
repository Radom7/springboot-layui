package com.haiyu.manager.controller.system;


import com.haiyu.manager.dto.PermissionDTO;
import com.haiyu.manager.pojo.BaseAdminPermission;
import com.haiyu.manager.pojo.BaseAdminUser;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.AdminPermissionService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: PermissionController
 * @Description: 权限管理
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/29 18:16
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminPermissionService permissionService;

    /**
     *
     * 功能描述: 跳到权限管理
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 9:22
     */
    @RequestMapping("permissionManage")
    public String permissionManage() {
        logger.info("进入权限管理");
        return "/permission/permissionManage";
    }


    /**
     *
     * 功能描述: 获取权限菜单列表
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 10:30
     */
    @PostMapping("permissionList")
    @ResponseBody
    public PageDataResult permissionList(@RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize){
        logger.info("获取权限菜单列表");
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取服务类目列表
            pdr = permissionService.getPermissionList(pageNum ,pageSize);
           logger.info("权限菜单列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("权限菜单列表查询异常！", e);
        }
        return pdr;
    }


    /**
     *
     * 功能描述: 获取根权限菜单列表
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 11:35
     */
    @GetMapping("parentPermissionList")
    @ResponseBody
    public List<PermissionDTO> parentPermissionList(){
        logger.info("获取根权限菜单列表");
        return permissionService.parentPermissionList();
    }




    /**
     *
     * 功能描述:设置权限[新增或更新]
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 9:42
     */
    @PostMapping("setPermission")
    @ResponseBody
    public Map<String,Object> setPermission(BaseAdminPermission permission) {
        logger.info("设置权限[新增或更新]！permission:" + permission);
        Map<String,Object> data = new HashMap();
        if(permission.getId() == null){
            //新增权限
            data = permissionService.addPermission(permission);
        }else{
            //修改权限
            data = permissionService.updatePermission(permission);
        }
        return data;
    }

    /**
     *
     * 功能描述: 删除权限菜单
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 12:02
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除权限菜单！id:" + id);
        Map<String, Object> data = new HashMap<>();
        //删除服务类目类型
        data = permissionService.del(id);
        return data;
    }



    /**
     *
     * 功能描述: 获取登陆用户的权限
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/4 9:48
     */
    @GetMapping("getUserPerms")
    @ResponseBody
    public Map<String, Object> getUserPerms(){
        logger.info("获取登陆用户的权限");
        Map<String, Object> data = new HashMap<>();
        BaseAdminUser user = (BaseAdminUser) SecurityUtils.getSubject().getPrincipal();
        data = permissionService.getUserPerms(user);
        return data;
    }

}
