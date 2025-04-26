package com.loong.x.chat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.chat.mapper.MessageMapper;
import com.loong.x.chat.model.entity.Message;
import com.loong.x.chat.model.vo.MessageVo;
import com.loong.x.chat.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 好友信息接口实现类
 * @Author: hdx
 * @Date: 2022/1/13 16:32
 * @Version: 1.0
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Override
    public List<MessageVo> list(MessageVo messageVo,Long userId) {
        // vo->entity
        Message message = new Message();
        BeanUtil.copyProperties(messageVo, message);
        message.setMineUserId(userId);
        System.out.println("----{}"+message);
        List<Message> messages = baseMapper.list(message);
        List<MessageVo> messagesVo = new ArrayList<>();
        // entity->vo
        messages.forEach(item -> {
            MessageVo msgVo = new MessageVo();
            BeanUtil.copyProperties(item, msgVo);
            messagesVo.add(msgVo);
        });
        return messagesVo;
    }
}
