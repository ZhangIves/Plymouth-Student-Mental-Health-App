package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysZone;
import com.loong.x.app.dao.SysZoneDao;
import com.loong.x.app.mapper.SysZoneMapper;
import com.loong.x.app.service.SysZoneService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysZone)表服务实现类
 *
 * @author makejava
 * @since 2024-04-24 23:52:33
 */
@Service("sysZoneService")
public class SysZoneServiceImpl extends ServiceImpl<SysZoneMapper,SysZone> implements SysZoneService {

}
