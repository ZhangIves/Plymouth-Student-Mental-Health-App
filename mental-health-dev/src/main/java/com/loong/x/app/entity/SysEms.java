package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysEms)实体类
 *
 * @author makejava
 * @since 2024-04-26 15:49:37
 */
@Data
public class SysEms implements Serializable {
    private static final long serialVersionUID = 428135995920934511L;

    private Integer id;
    private Integer sort;

    private String name;

    private Integer capacity;

    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}

