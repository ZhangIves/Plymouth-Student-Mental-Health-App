package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysZoneType)实体类
 *
 * @author makejava
 * @since 2024-05-10 23:35:54
 */
@Data
public class SysZoneType implements Serializable {
    private static final long serialVersionUID = 804531021614828029L;

    private Integer id;

    private String name;

    private Integer sort;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

