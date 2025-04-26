package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysShops)实体类
 *
 * @author makejava
 * @since 2024-04-27 09:46:22
 */
@Data
public class SysShops implements Serializable {
    private static final long serialVersionUID = -51372207351021979L;

    private String id;

    private String ownerId;

    private String ownerIdCard;

    private String buildingId;

    private String buildName;

    private Integer type;

    private String shopsStatus;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String remark;

    private String area;

    private String floor;

    private Integer rent;

}

