package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.app.entity.SysRepairLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SysRepairLog)表服务接口
 *
 * @author makejava
 * @since 2024-04-24 15:10:15
 */
public interface SysRepairLogService extends IService<SysRepairLog> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRepairLog queryById(Long id);

    /**
     * 分页查询
     *
     * @param sysRepairLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysRepairLog> queryByPage(SysRepairLog sysRepairLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysRepairLog 实例对象
     * @return 实例对象
     */
    SysRepairLog insert(SysRepairLog sysRepairLog);

    /**
     * 修改数据
     *
     * @param sysRepairLog 实例对象
     * @return 实例对象
     */
    SysRepairLog update(SysRepairLog sysRepairLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
