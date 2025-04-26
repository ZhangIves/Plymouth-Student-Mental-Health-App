package com.loong.x.chat.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.loong.x.common.entity.BaseEntity;
import lombok.Data;

/**
 * (Group)群组实体
 *
 * @author makejava
 * @since 2024-04-26 00:25:55
 */

@Data
@TableName("`group`")
public class Group extends BaseEntity {

    /**
     * 群名称
     */
    private String name;

    /**
     * 群头像
     */
    private String avatar;

    /**
     * 群公告
     */
    private String notice;

    /**
     * 群介绍
     */
    private String intro;

    /**
     * 组名称首字母
     */
    private String Alphabetic;

    /**
     * 群主
     */
    private Long adminUserId;

    /**
     * 状态 1：正常；0：删除
     */
    private byte status;

}
