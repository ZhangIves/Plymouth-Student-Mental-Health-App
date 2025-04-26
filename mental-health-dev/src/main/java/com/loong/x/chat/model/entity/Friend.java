package com.loong.x.chat.model.entity;

import com.loong.x.common.entity.BaseEntity;
import lombok.Data;

/**
 * (Friend)好友实体
 *
 * @author makejava
 * @since 2024-04-26 00:25:55
 */
@Data
public class Friend extends BaseEntity {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 好友Id
     */
    private Long friendId;

    /**
     * 好友昵称
     */
    private String nickname;

    /**
     * 好友头像
     */
    private String avatar;

    /**
     * 好友昵称首字母
     */
    private String alphabetic;

    /**
     * 状态 1：正常；0：删除
     */
    private byte status;

}
