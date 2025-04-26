package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysOwner)实体类
 *
 * @author makejava
 * @since 2024-04-24 15:45:37
 */
@Data
public class SysOwner implements Serializable {
    private static final long serialVersionUID = -85140349921698026L;

    private Long id;

    private String ownerId;

    private String ownerName;

    private String ownerPhone;

    private Integer ownerNumber;

    private Integer ownerStatus;

    private String ownerIdCard;

    private String sex;

    private String location;

    private String purpose;

    private String type;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String remark;

}

