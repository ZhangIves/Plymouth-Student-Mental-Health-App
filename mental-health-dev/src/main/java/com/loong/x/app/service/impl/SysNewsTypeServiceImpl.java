package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysNewsType;
import com.loong.x.app.dao.SysNewsTypeDao;
import com.loong.x.app.mapper.SysNewsTypeMapper;
import com.loong.x.app.service.SysNewsTypeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysNewsType)表服务实现类
 *
 * @author makejava
 * @since 2024-05-10 22:30:22
 */
@Service("sysNewsTypeService")
public class SysNewsTypeServiceImpl extends ServiceImpl<SysNewsTypeMapper,SysNewsType> implements SysNewsTypeService {
    @Resource
    private SysNewsTypeDao sysNewsTypeDao;


}
