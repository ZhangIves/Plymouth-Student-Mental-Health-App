package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysZoneLog)实体类
 *
 * @author makejava
 * @since 2024-05-16 17:09:30
 */
@Data
public class SysZoneLog implements Serializable {
    private static final long serialVersionUID = 538968959858373862L;

    private String id;
    /**
     * 用户id
     */
    private String userId;

    private String userName;
    /**
     * 话题id
     */
    private String zoneId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间（无用）
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer status;

    private String remark;

}

