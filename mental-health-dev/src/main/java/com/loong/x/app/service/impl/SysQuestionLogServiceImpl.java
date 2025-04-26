package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysQuestionLog;
import com.loong.x.app.dao.SysQuestionLogDao;
import com.loong.x.app.mapper.SysQuestionLogMapper;
import com.loong.x.app.service.SysQuestionLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysQuestionLog)表服务实现类
 *
 * @author makejava
 * @since 2024-05-12 17:19:27
 */
@Service("sysQuestionLogService")
public class SysQuestionLogServiceImpl extends ServiceImpl<SysQuestionLogMapper,SysQuestionLog> implements SysQuestionLogService {
    @Resource
    private SysQuestionLogDao sysQuestionLogDao;
}
