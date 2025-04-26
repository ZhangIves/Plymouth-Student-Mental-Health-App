package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysRolesMenus;
import com.loong.x.app.dao.SysRolesMenusDao;
import com.loong.x.app.mapper.SysRoleMenuMapper;
import com.loong.x.app.service.SysRolesMenusService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 角色菜单关联(SysRolesMenus)表服务实现类
 *
 * @author makejava
 * @since 2024-04-22 01:47:23
 */
@Service("sysRolesMenusService")
public class SysRolesMenusServiceImpl extends ServiceImpl<SysRoleMenuMapper,SysRolesMenus> implements SysRolesMenusService {
    @Resource
    private SysRolesMenusDao sysRolesMenusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRolesMenus queryById(String id) {
        return this.sysRolesMenusDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysRolesMenus 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysRolesMenus> queryByPage(SysRolesMenus sysRolesMenus, PageRequest pageRequest) {
        long total = this.sysRolesMenusDao.count(sysRolesMenus);
        return new PageImpl<>(this.sysRolesMenusDao.queryAllByLimit(sysRolesMenus, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysRolesMenus 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolesMenus insert(SysRolesMenus sysRolesMenus) {
        this.sysRolesMenusDao.insert(sysRolesMenus);
        return sysRolesMenus;
    }

    /**
     * 修改数据
     *
     * @param sysRolesMenus 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolesMenus update(SysRolesMenus sysRolesMenus) {
        this.sysRolesMenusDao.update(sysRolesMenus);
        return this.queryById(sysRolesMenus.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sysRolesMenusDao.deleteById(id) > 0;
    }
}
