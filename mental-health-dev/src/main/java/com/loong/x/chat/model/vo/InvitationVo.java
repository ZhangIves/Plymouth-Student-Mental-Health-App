package com.loong.x.chat.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.loong.x.common.vo.BaseVo;
import lombok.Data;


/**
 * @Description: 好友邀请Vo
 * @Author: hdx
 * @Date: 2022/1/30 11:22
 * @Version: 1.0
 */
@Data
public class InvitationVo extends BaseVo {

    /**
     * 用户Id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 好友Id
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
     * 头像
     */
    private String avatar;

    /**
     * 好友头像
     */
    private String friendAvatar;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 状态
     */
    private byte status;

    /**
     * 拒绝理由
     */
    private String reason;

}
