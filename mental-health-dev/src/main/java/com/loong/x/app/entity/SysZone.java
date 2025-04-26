package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysZone)实体类
 *
 * @author makejava
 * @since 2024-05-16 15:45:53
 */
@Data
public class SysZone implements Serializable {
    private static final long serialVersionUID = -43211745503397435L;

    private String id;
    /**
     * 话题
     */
    private String zoneTitle;
    /**
     * 内容
     */
    private String zoneContent;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 类型Id
     */
    private Integer zoneTypeId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 图片
     */
    private String zoneImageUrl;
    /**
     * 数量
     */
    private Integer zoneCapacity;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;


}

