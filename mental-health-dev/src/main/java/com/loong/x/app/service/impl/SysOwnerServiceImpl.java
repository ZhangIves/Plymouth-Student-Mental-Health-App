package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysOwner;
import com.loong.x.app.dao.SysOwnerDao;
import com.loong.x.app.mapper.SysOwnerMapper;
import com.loong.x.app.service.SysOwnerService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysOwner)表服务实现类
 *
 * @author makejava
 * @since 2024-04-24 15:45:37
 */
@Service("sysOwnerService")
public class SysOwnerServiceImpl extends ServiceImpl<SysOwnerMapper,SysOwner> implements SysOwnerService {
}
