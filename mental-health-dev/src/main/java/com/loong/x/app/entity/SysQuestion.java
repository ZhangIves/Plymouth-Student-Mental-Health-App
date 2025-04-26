package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysQuestion)实体类
 *
 * @author makejava
 * @since 2024-05-12 17:19:26
 */
@Data
public class SysQuestion implements Serializable {
    private static final long serialVersionUID = -62721354732322765L;

    private Integer id;

    private Integer zoneId;

    private String title;

    private String content;

    private String userId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    private Integer status;

}

