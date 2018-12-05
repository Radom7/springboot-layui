package com.haiyu.manager.pojo;

import javax.persistence.*;

@Table(name = "base_admin_permission")
public class BaseAdminPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单id
     */
    private Integer pid;

    /**
     * 描述
     */
    private String descpt;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 删除标志（0:删除 1：存在）
     */
    @Column(name = "del_flag")
    private Integer delFlag;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取菜单名称
     *
     * @return name - 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父菜单id
     *
     * @return pid - 父菜单id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父菜单id
     *
     * @param pid 父菜单id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取描述
     *
     * @return descpt - 描述
     */
    public String getDescpt() {
        return descpt;
    }

    /**
     * 设置描述
     *
     * @param descpt 描述
     */
    public void setDescpt(String descpt) {
        this.descpt = descpt;
    }

    /**
     * 获取菜单url
     *
     * @return url - 菜单url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单url
     *
     * @param url 菜单url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
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
     * 获取删除标志（0:删除 1：存在）
     *
     * @return del_flag - 删除标志（0:删除 1：存在）
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标志（0:删除 1：存在）
     *
     * @param delFlag 删除标志（0:删除 1：存在）
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "BaseAdminPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", descpt='" + descpt + '\'' +
                ", url='" + url + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}