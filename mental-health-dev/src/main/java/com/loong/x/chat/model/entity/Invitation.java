package com.loong.x.chat.model.entity;

import com.loong.x.common.entity.BaseEntity;
import lombok.Data;

/**
 * (Invitation)好友邀请表
 *
 * @author makejava
 * @since 2024-04-26 00:25:55
 */
@Data
public class Invitation extends BaseEntity {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 好友Id
     */
    private Long friendId;

    /**
     * 用户昵称
     */
    private String userNickname;

    /**
     * 好友昵称
     */
    private String friendNickname;

    /**
     * 好友头像
     */
    private String friendAvatar;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 状态(0-待通过; 1-已通过; 2-拒绝)
     */
    private byte status;

    /**
     * 拒绝理由
     */
    private String reason;
}
