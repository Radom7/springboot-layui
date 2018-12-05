package com.haiyu.manager.controller.system;

import com.haiyu.manager.pojo.BaseAdminRole;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.AdminRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Title: RoleController
 * @Description: 角色管理
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/21 13:43
 */
@Controller
@RequestMapping("role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 跳转到角色管理
     * @return
     */
    @RequestMapping("/roleManage")
    public String toPage() {
        logger.info("进入角色管理");
        return "/role/roleManage";
    }

    /**
     *
     * 功能描述: 获取角色列表
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/21 14:29
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult getRoleList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {
        logger.info("获取角色列表");
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取角色列表
            pdr = adminRoleService.getRoleList(pageNum ,pageSize);
            logger.info("角色列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("角色列表查询异常！", e);
        }
        return pdr;
    }

    /**
     *
     * 功能描述: 获取角色列表
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/3 13:22
     */
    @GetMapping("getRoles")
    @ResponseBody
    public List<BaseAdminRole> getRoles(){
        logger.info("获取角色列表");
        return adminRoleService.getRoles();
    }

    /**
     *
     *述: 设置角色[新增或更新]
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/3 10:54
     */
    @PostMapping("setRole")
    @ResponseBody
    public Map<String,Object> setRole(BaseAdminRole role) {
        logger.info("设置角色[新增或更新]！role:" + role);
        Map<String,Object> data = new HashMap();
        if(role.getId() == null){
            //新增角色
            data = adminRoleService.addRole(role);
        }else{
            //修改角色
            data = adminRoleService.updateRole(role);
        }
        return data;
    }


    /**
     *
     * 功能描述: 删除/恢复角色
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/21 16:00
     */
    @PostMapping("updateRoleStatus")
    @ResponseBody
    public Map<String,Object> updateRoleStatus(@RequestParam("id") int id,@RequestParam("status") Integer status) {
        logger.info("删除/恢复角色！id:" + id+" status:"+status);
        Map<String, Object> data = new HashMap<>();
        if(status == 0){
            //删除角色
            data = adminRoleService.delRole(id,status);
        }else{
            //恢复角色
            data = adminRoleService.recoverRole(id,status);
        }
        return data;
    }

}
