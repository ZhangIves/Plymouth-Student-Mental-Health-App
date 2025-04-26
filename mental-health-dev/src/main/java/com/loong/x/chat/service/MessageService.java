package com.loong.x.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.chat.model.entity.Message;
import com.loong.x.chat.model.vo.MessageVo;

import java.util.List;

/**
 * @Description: 好友/群组消息接口
 * @Author: hdx
 * @Date: 2022/1/13 16:27
 * @Version: 1.0
 */
public interface MessageService extends IService<Message> {

    /**
     * 消息分页列表
     *
     */
    List<MessageVo> list(MessageVo messageVo,Long userId);

}
