package com.loong.x.chat.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.loong.x.common.vo.BaseVo;
import lombok.Data;

/**
 * 好友信息Vo
 *
 * @author makejava
 * @since 2024-04-26 00:25:55
 */
@Data
public class FriendMsgVo extends BaseVo {

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 消息类型(0-系统消息;1-文字;2-图片;3-语音;4-视频)
     */
    private byte msgType;

    /**
     * 消息发送方
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long fromUserId;

    /**
     * 消息接收方
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long toUserId;

    /**
     * 状态(0-未读;1-已读;2-撤回;3-删除)
     */
    private byte status;

    /**
     * 消息来源(0-好友;1-群组)
     */
    private byte source;

    /**
     * 语音时长
     */
    private String time;

}
