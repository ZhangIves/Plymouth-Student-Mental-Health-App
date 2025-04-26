package com.loong.x.chat.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.loong.x.common.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 未读消息表
 * @author: hdx
 * @Date: 2022-09-06 11:27
 * @version: 1.0
 **/
@Data
@Accessors(chain = true)
public class MsgUnreadRecordVo extends BaseEntity {

    /**
     * 目标方Id(对应好友/群组 id)
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long targetId;

    /**
     * 用户Id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 未读数量
     */
    private int unreadNum;

}
