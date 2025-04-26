package com.loong.x.chat.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.loong.x.common.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @Description: 群组Vo
 * @author: hdx
 * @Date: 2022-06-14 10:46
 * @version: 1.0
 **/
@Data
public class GroupVo extends BaseEntity {

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
     * 群主Id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long adminUserId;

    /**
     * 状态 1：正常；0：删除
     */
    private byte status;

    /**
     * 群组员
     */
    private List<GroupUserVo> groupUsers;

}
