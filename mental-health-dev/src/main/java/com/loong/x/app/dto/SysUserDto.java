package com.loong.x.app.dto;

import com.loong.x.app.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/4/22 1:38
 */
//@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class SysUserDto{
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户真实姓名
     */
    private String fullName;
    /**
     * 密码
     */
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 手机号
     */
    private String phone;

    private Integer sex;

    private String avatar;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private Date loginDate;

    private Date createTime;

    private Date updateTime;

    private String remark;
    private String roleId;
    private String roleName;
    private String roleIdList;

    private List<Integer> roleIdSet;
}
