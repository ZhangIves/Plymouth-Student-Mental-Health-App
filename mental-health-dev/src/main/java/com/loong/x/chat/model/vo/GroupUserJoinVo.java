package com.loong.x.chat.model.vo;

import com.loong.x.common.entity.BaseEntity;
import lombok.Data;

/**
 * @Description: 群组用户拉入Vo
 * @author: hdx
 * @Date: 2022-06-14 10:46
 * @version: 1.0
 **/
@Data
public class GroupUserJoinVo extends BaseEntity {

    /**
     * 群组id
     */
    private Long groupId;

    /**
     * 用户ids(逗号隔开)
     */
    private String userIds;
}
