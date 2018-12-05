package com.haiyu.manager.dto;


import lombok.Data;

import java.util.List;

/**
 * @Title: PermissionDTO
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/30 11:22
 */
@Data
public class PermissionDTO {
    private Integer id;

    private String name;

    private Integer pid;

    private String pname;

    private String descpt;

    private String url;

    private String createTime;

    private String updateTime;

    private Integer delFlag;

    List<PermissionDTO> childrens;
}
