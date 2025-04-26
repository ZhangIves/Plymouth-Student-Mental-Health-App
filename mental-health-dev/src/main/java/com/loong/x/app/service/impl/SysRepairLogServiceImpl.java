package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysRepairLog;
import com.loong.x.app.dao.SysRepairLogDao;
import com.loong.x.app.mapper.SysRepairLogMapper;
import com.loong.x.app.service.SysRepairLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysRepairLog)表服务实现类
 *
 * @author makejava
 * @since 2024-04-24 15:10:15
 */
@Service("sysRepairLogService")
public class SysRepairLogServiceImpl extends ServiceImpl<SysRepairLogMapper,SysRepairLog> implements SysRepairLogService {
    @Resource
    private SysRepairLogDao sysRepairLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRepairLog queryById(Long id) {
        return this.sysRepairLogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysRepairLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysRepairLog> queryByPage(SysRepairLog sysRepairLog, PageRequest pageRequest) {
        long total = this.sysRepairLogDao.count(sysRepairLog);
        return new PageImpl<>(this.sysRepairLogDao.queryAllByLimit(sysRepairLog, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysRepairLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysRepairLog insert(SysRepairLog sysRepairLog) {
        this.sysRepairLogDao.insert(sysRepairLog);
        return sysRepairLog;
    }

    /**
     * 修改数据
     *
     * @param sysRepairLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysRepairLog update(SysRepairLog sysRepairLog) {
        this.sysRepairLogDao.update(sysRepairLog);
        return this.queryById(sysRepairLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysRepairLogDao.deleteById(id) > 0;
    }
}
