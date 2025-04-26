package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysNewsType)实体类
 *
 * @author makejava
 * @since 2024-05-10 22:30:22
 */
@Data
public class SysNewsType implements Serializable {
    private static final long serialVersionUID = -90204718287996587L;

    private Integer id;

    private String name;

    private Integer sort;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

