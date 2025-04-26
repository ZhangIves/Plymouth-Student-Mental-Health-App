package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysImage;
import com.loong.x.app.dao.SysImageDao;
import com.loong.x.app.mapper.SysImageMapper;
import com.loong.x.app.service.SysImageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysImage)表服务实现类
 *
 * @author makejava
 * @since 2024-04-24 15:10:35
 */
@Service("sysImageService")
public class SysImageServiceImpl extends ServiceImpl<SysImageMapper,SysImage> implements SysImageService {
    @Resource
    private SysImageDao sysImageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysImage queryById(Long id) {
        return this.sysImageDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysImage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysImage> queryByPage(SysImage sysImage, PageRequest pageRequest) {
        long total = this.sysImageDao.count(sysImage);
        return new PageImpl<>(this.sysImageDao.queryAllByLimit(sysImage, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysImage 实例对象
     * @return 实例对象
     */
    @Override
    public SysImage insert(SysImage sysImage) {
        this.sysImageDao.insert(sysImage);
        return sysImage;
    }

    /**
     * 修改数据
     *
     * @param sysImage 实例对象
     * @return 实例对象
     */
    @Override
    public SysImage update(SysImage sysImage) {
        this.sysImageDao.update(sysImage);
        return this.queryById(sysImage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysImageDao.deleteById(id) > 0;
    }
}
