package com.loong.x.chat.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.loong.x.common.vo.BaseVo;
import lombok.Data;

/**
 * @Description: 好友Vo
 * @Author: hdx
 * @Date: 2022/1/30 11:22
 * @Version: 1.0
 */
@Data
public class FriendVo extends BaseVo {

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
     * 昵称
     */
    private String nickname;

    /**
     * 好友头像
     */
    private String avatar;

    /**
     * 字母索引
     */
    private String alphabetic;

    /**
     * 状态
     */
    private byte status;

}
