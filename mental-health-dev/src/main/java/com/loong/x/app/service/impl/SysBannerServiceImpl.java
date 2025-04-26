package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysBanner;
import com.loong.x.app.dao.SysBannerDao;
import com.loong.x.app.mapper.SysBannerMapper;
import com.loong.x.app.service.SysBannerService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysBanner)表服务实现类
 *
 * @author makejava
 * @since 2024-05-11 10:17:20
 */
@Service("sysBannerService")
public class SysBannerServiceImpl extends ServiceImpl<SysBannerMapper,SysBanner> implements SysBannerService {
    @Resource
    private SysBannerDao sysBannerDao;
}
