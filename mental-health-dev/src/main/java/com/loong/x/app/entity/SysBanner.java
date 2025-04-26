package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysBanner)实体类
 *
 * @author makejava
 * @since 2024-05-11 10:17:20
 */
@Data
public class SysBanner implements Serializable {
    private static final long serialVersionUID = 311780158748451594L;

    private Long id;

    private String img;

    private String title;

    private Integer status;

    private Integer sortNum;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}

