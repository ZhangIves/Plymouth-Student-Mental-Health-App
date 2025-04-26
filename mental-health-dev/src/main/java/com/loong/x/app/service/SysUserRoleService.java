package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.app.entity.SysUserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SysUserRole)表服务接口
 *
 * @author makejava
 * @since 2024-04-23 14:33:54
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRole queryById(String id);

    /**
     * 分页查询
     *
     * @param sysUserRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysUserRole> queryByPage(SysUserRole sysUserRole, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    SysUserRole insert(SysUserRole sysUserRole);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    SysUserRole update(SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
