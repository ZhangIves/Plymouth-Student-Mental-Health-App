package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.dao.SysNewsDao;
import com.loong.x.app.entity.SysNews;
import com.loong.x.app.mapper.SysNewsMapper;
import com.loong.x.app.service.SysNewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (SysNews)表服务实现类
 *
 * @author makejava
 * @since 2024-05-11 14:07:09
 */
@Service("sysNewsService")
public class SysNewsServiceImpl extends ServiceImpl<SysNewsMapper,SysNews> implements SysNewsService {
    @Resource
    private SysNewsDao sysNewsDao;
}
