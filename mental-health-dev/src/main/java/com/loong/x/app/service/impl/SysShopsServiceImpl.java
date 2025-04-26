package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysShops;
import com.loong.x.app.dao.SysShopsDao;
import com.loong.x.app.mapper.SysShopsMapper;
import com.loong.x.app.service.SysShopsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysShops)表服务实现类
 *
 * @author makejava
 * @since 2024-04-27 09:46:22
 */
@Service("sysShopsService")
public class SysShopsServiceImpl extends ServiceImpl<SysShopsMapper,SysShops> implements SysShopsService {
}
