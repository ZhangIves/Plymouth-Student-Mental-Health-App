package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.app.dto.SysUserDto;
import com.loong.x.app.entity.SysUser;
import com.loong.x.common.custom.PaginationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2024-04-22 00:45:34
 */
public interface SysUserService extends IService<SysUser> {

    PaginationResult<SysUserDto> queryByPageWithRole(SysUserDto sysUser, PageRequest pageRequest);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(String id);

    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysUser> queryByPage(SysUser sysUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
