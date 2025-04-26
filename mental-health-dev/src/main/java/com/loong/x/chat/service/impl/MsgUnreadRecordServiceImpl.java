package com.loong.x.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.chat.mapper.MsgUnreadRecordMapper;
import com.loong.x.chat.model.entity.MsgUnreadRecord;
import com.loong.x.chat.service.MsgUnreadRecordService;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息未读标识接口实现类
 * @author: hdx
 * @Date: 2022-08-25 09:33
 * @version: 1.0
 **/
@Service
public class MsgUnreadRecordServiceImpl extends ServiceImpl<MsgUnreadRecordMapper, MsgUnreadRecord> implements MsgUnreadRecordService {

    @Override
    public void update(MsgUnreadRecord msgUnreadRecord) {
        this.saveOrUpdate(msgUnreadRecord);
    }
}
