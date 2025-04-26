package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysQuestion;
import com.loong.x.app.dao.SysQuestionDao;
import com.loong.x.app.mapper.SysQuestionMapper;
import com.loong.x.app.service.SysQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysQuestion)表服务实现类
 *
 * @author makejava
 * @since 2024-05-12 17:19:26
 */
@Service("sysQuestionService")
public class SysQuestionServiceImpl extends ServiceImpl<SysQuestionMapper,SysQuestion> implements SysQuestionService {
    @Resource
    private SysQuestionDao sysQuestionDao;

}
