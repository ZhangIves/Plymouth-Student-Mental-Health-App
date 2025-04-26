package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysZoneLog;
import com.loong.x.app.dao.SysZoneLogDao;
import com.loong.x.app.mapper.SysZoneLogMapper;
import com.loong.x.app.service.SysZoneLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysZoneLog)表服务实现类
 *
 * @author makejava
 * @since 2024-04-25 13:20:11
 */
@Service("sysZoneLogService")
public class SysZoneLogServiceImpl extends ServiceImpl<SysZoneLogMapper,SysZoneLog> implements SysZoneLogService {
}
