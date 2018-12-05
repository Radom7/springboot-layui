package com.haiyu.manager.pojo;

import javax.persistence.*;

@Table(name = "base_admin_role")
public class BaseAdminRole {
    /**
     * 权限角色ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "role_desc")
    private String roleDesc;

    /**
     * 权限
     */
    private String permissions;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 1：有效 
            0：无效
     */
    @Column(name = "role_status")
    private Integer roleStatus;

    /**
     * 获取权限角色ID
     *
     * @return id - 权限角色ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置权限角色ID
     *
     * @param id 权限角色ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色描述
     *
     * @return role_desc - 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 设置角色描述
     *
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * 获取权限
     *
     * @return permissions - 权限
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     * 设置权限
     *
     * @param permissions 权限
     */
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取1：有效 
            0：无效
     *
     * @return role_status - 1：有效 
            0：无效
     */
    public Integer getRoleStatus() {
        return roleStatus;
    }

    /**
     * 设置1：有效 
            0：无效
     *
     * @param roleStatus 1：有效 
            0：无效
     */
    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }
}