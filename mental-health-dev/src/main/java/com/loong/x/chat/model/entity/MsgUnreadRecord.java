package com.loong.x.chat.model.entity;

import com.loong.x.common.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (MsgUnreadRecord)未读消息表
 *
 * @author makejava
 * @since 2024-04-26 00:25:55
 */
@Data
@Accessors(chain = true)
public class MsgUnreadRecord extends BaseEntity {

    /**
     * 目标方Id(对应好友/群组 id)
     */
    private Long targetId;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 未读数量
     */
    private int unreadNum;

    /**
     * 消息来源(0-好友;1-群组)
     */
    private byte source;

}
