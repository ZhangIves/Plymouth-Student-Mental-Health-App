package com.loong.x.chat.model.vo;

import com.loong.x.common.vo.BaseVo;
import lombok.Data;

/**
 * @Description: 消息处理Vo
 * @author: hdx
 * @Date: 2022-06-14 10:46
 * @version: 1.0
 **/
@Data
public class MsgHandleVo extends BaseVo {

    /**
     * 处理类型(1-撤销;1-删除
     */
    private byte type;

    /**
     * 消息内容
     */
    private MessageVo message;
}
