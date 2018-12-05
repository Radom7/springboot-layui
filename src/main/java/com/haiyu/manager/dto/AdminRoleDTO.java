package com.haiyu.manager.dto;

import lombok.Data;


/**
 * @Title: AdminRoleDTO
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/12/3 18:51
 */
@Data
public class AdminRoleDTO {
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    private String permissionIds;
    /**
     * 权限
     */
    private String permissions;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 1：有效
     0：无效
     */
    private Integer roleStatus;
}
