package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysEms;
import com.loong.x.app.dao.SysEmsDao;
import com.loong.x.app.mapper.SysEmsMapper;
import com.loong.x.app.service.SysEmsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysEms)表服务实现类
 *
 * @author makejava
 * @since 2024-04-26 15:49:37
 */
@Service("sysEmsService")
public class SysEmsServiceImpl extends ServiceImpl<SysEmsMapper,SysEms> implements SysEmsService {
}
