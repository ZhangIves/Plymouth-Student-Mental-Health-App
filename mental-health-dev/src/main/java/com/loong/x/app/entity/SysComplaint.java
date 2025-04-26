package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysComplaint)实体类
 *
 * @author makejava
 * @since 2024-04-26 16:41:34
 */
@Data
public class SysComplaint implements Serializable {
    private static final long serialVersionUID = -80005783826260776L;

    private Integer id;

    private String userId;

    private String userName;

    private String phone;

    private String imageUrl;

    private String title;

    private String address;

    private String text;

    private Integer isExamine;

    private String examineData;

    private Date date;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

