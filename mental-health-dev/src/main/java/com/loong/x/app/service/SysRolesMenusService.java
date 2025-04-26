package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.app.entity.SysRolesMenus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 角色菜单关联(SysRolesMenus)表服务接口
 *
 * @author makejava
 * @since 2024-04-22 01:47:23
 */
public interface SysRolesMenusService extends IService<SysRolesMenus> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRolesMenus queryById(String id);

    /**
     * 分页查询
     *
     * @param sysRolesMenus 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysRolesMenus> queryByPage(SysRolesMenus sysRolesMenus, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysRolesMenus 实例对象
     * @return 实例对象
     */
    SysRolesMenus insert(SysRolesMenus sysRolesMenus);

    /**
     * 修改数据
     *
     * @param sysRolesMenus 实例对象
     * @return 实例对象
     */
    SysRolesMenus update(SysRolesMenus sysRolesMenus);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
