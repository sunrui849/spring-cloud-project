package com.sr.cloud.user.dto;

import lombok.Data;

import java.util.List;

// todo 需不需要修改
@Data
public class PrivilegeDTO {
    /**
     * 节点id
     */
    private Long id;
    /**
     * 父节点id,0为一级节点
     */
    private Long parentId;
    /**
     * 节点code码
     */
    private String privilegeCode;
    /**
     * 节点名称
     */
    private String privilegeName;
    /**
     * 菜单节点访问路径
     */
    private String url;
    /**
     * 权限类型 0:左侧菜单权限 1:顶部菜单权限 2:数据指标权限
     */
    private Integer privilegeType;
    /**
     * 是否是父节点 0:不是 1:是
     */
    private Integer isParent;
    /**
     * 菜单级别 1:一级菜单 2:二级菜单 3:三级菜单
     */
    private Integer levels;
    /**
     * 在同级菜单节点的排序位置
     */
    private Integer sortNum;
    /**
     * 下一级菜单列表
     */
    private List<PrivilegeDTO> children;
}
