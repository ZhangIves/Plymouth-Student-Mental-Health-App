package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2024-04-22 00:45:34
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 719011481945026126L;

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

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String remark;

}

