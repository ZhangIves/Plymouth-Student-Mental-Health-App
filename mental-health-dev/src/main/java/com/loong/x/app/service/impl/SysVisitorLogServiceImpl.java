package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysVisitorLog;
import com.loong.x.app.dao.SysVisitorLogDao;
import com.loong.x.app.mapper.SysVisitorLogMapper;
import com.loong.x.app.service.SysVisitorLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysVisitorLog)表服务实现类
 *
 * @author makejava
 * @since 2024-04-24 13:31:56
 */
@Service("sysVisitorLogService")
public class SysVisitorLogServiceImpl extends ServiceImpl<SysVisitorLogMapper,SysVisitorLog> implements SysVisitorLogService {
    @Resource
    private SysVisitorLogDao sysVisitorLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysVisitorLog queryById(Integer id) {
        return this.sysVisitorLogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysVisitorLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysVisitorLog> queryByPage(SysVisitorLog sysVisitorLog, PageRequest pageRequest) {
        long total = this.sysVisitorLogDao.count(sysVisitorLog);
        return new PageImpl<>(this.sysVisitorLogDao.queryAllByLimit(sysVisitorLog, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysVisitorLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysVisitorLog insert(SysVisitorLog sysVisitorLog) {
        this.sysVisitorLogDao.insert(sysVisitorLog);
        return sysVisitorLog;
    }

    /**
     * 修改数据
     *
     * @param sysVisitorLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysVisitorLog update(SysVisitorLog sysVisitorLog) {
        this.sysVisitorLogDao.update(sysVisitorLog);
        return this.queryById(sysVisitorLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysVisitorLogDao.deleteById(id) > 0;
    }
}
