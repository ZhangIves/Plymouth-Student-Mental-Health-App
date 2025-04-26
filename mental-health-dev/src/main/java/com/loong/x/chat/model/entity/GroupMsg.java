package com.loong.x.chat.model.entity;

import com.loong.x.common.entity.BaseEntity;
import lombok.Data;

/**
 * (GroupMsg)群组消息
 *
 * @author makejava
 * @since 2024-04-26 00:25:55
 */
@Data
public class GroupMsg extends BaseEntity {

    /**
     * 群组id
     */
    private Long groupId;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 消息类型(0-系统消息;1-文字;2-图片;3-语音;4-视频)
     */
    private byte msgType;

    /**
     * 发送方Id
     */
    private Long fromUserId;

    /**
     * 发送方昵称
     */
    private String fromUserNickname;

    /**
     * 发送方头像
     */
    private String fromUserAvatar;

    /**
     * 语音时长
     */
    private String time;

    /**
     * 状态 1：正常；0：删除
     */
    private byte status;

}
