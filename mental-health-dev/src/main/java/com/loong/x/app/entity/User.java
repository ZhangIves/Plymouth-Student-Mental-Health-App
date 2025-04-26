package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户信息(User)实体类
 *
 * @author makejava
 * @since 2024-03-13 09:42:28
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 359538576074782491L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;

    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 身份证号
     */
    private String idNumber;

    private String roles;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 会员卡号
     */
    private Integer memberId;
    /**
     * 会员余额
     */
    private Integer memberBalance;
    /**
     * 会员积分
     */
    private Integer memberPoints;

    private String memberType;
    private Date memberStartTime;
    private Date memberEndTime;

}

