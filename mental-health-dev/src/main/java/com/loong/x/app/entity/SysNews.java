package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysNews)实体类
 *
 * @author makejava
 * @since 2024-05-11 14:07:09
 */
@Data
public class SysNews implements Serializable {
    private static final long serialVersionUID = 819287257443146967L;

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 排序（序号越大越靠前）
     */
    private Integer sort;
    /**
     * 状态（0：禁止；1：显示）
     */
    private Integer status;
    /**
     * 新闻类型
     */
    private Integer typeId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 创建用户id
     */
    private String createUserId;
    /**
     * 备注
     */
    private String remarks;

    private String newsImageUrl;

}

