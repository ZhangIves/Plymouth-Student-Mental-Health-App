package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.app.entity.SysVisitorLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SysVisitorLog)表服务接口
 *
 * @author makejava
 * @since 2024-04-24 13:31:56
 */
public interface SysVisitorLogService extends IService<SysVisitorLog> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysVisitorLog queryById(Integer id);

    /**
     * 分页查询
     *
     * @param sysVisitorLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysVisitorLog> queryByPage(SysVisitorLog sysVisitorLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysVisitorLog 实例对象
     * @return 实例对象
     */
    SysVisitorLog insert(SysVisitorLog sysVisitorLog);

    /**
     * 修改数据
     *
     * @param sysVisitorLog 实例对象
     * @return 实例对象
     */
    SysVisitorLog update(SysVisitorLog sysVisitorLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
