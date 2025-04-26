package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysZoneType;
import com.loong.x.app.dao.SysZoneTypeDao;
import com.loong.x.app.mapper.SysZoneTypeMapper;
import com.loong.x.app.service.SysZoneTypeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysZoneType)表服务实现类
 *
 * @author makejava
 * @since 2024-05-10 23:35:54
 */
@Service("sysZoneTypeService")
public class SysZoneTypeServiceImpl extends ServiceImpl<SysZoneTypeMapper,SysZoneType> implements SysZoneTypeService {
    @Resource
    private SysZoneTypeDao sysZoneTypeDao;
}
