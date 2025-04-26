package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysUserRole;
import com.loong.x.app.dao.SysUserRoleDao;
import com.loong.x.app.mapper.SysRoleMapper;
import com.loong.x.app.mapper.SysUserRoleMapper;
import com.loong.x.app.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2024-04-23 14:33:55
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper,SysUserRole> implements SysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserRole queryById(String id) {
        return this.sysUserRoleDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysUserRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysUserRole> queryByPage(SysUserRole sysUserRole, PageRequest pageRequest) {
        long total = this.sysUserRoleDao.count(sysUserRole);
        return new PageImpl<>(this.sysUserRoleDao.queryAllByLimit(sysUserRole, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRole insert(SysUserRole sysUserRole) {
        this.sysUserRoleDao.insert(sysUserRole);
        return sysUserRole;
    }

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRole update(SysUserRole sysUserRole) {
        this.sysUserRoleDao.update(sysUserRole);
        return this.queryById(sysUserRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sysUserRoleDao.deleteById(id) > 0;
    }
}
