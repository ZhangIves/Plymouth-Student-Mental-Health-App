package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.app.entity.SysImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SysImage)表服务接口
 *
 * @author makejava
 * @since 2024-04-24 15:10:35
 */
public interface SysImageService extends IService<SysImage> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysImage queryById(Long id);

    /**
     * 分页查询
     *
     * @param sysImage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysImage> queryByPage(SysImage sysImage, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysImage 实例对象
     * @return 实例对象
     */
    SysImage insert(SysImage sysImage);

    /**
     * 修改数据
     *
     * @param sysImage 实例对象
     * @return 实例对象
     */
    SysImage update(SysImage sysImage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
