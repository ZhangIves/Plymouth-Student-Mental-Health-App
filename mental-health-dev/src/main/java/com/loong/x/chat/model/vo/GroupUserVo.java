package com.loong.x.chat.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.loong.x.common.entity.BaseEntity;
import lombok.Data;


/**
 * Description
 * 群组用户Vo
 * @author wcloong
 * @version 0.1.0
 * @since 2022/2/7 15:50
 */
@Data
public class GroupUserVo extends BaseEntity {

    /**
     * 群组id
     */
    private Long groupId;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 用户昵称
     */
    private String userNickname;

    /**
     * 用户头像
     */
    private String userAvatar;

}
