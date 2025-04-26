package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.dto.SysUserDto;
import com.loong.x.app.entity.SysUser;
import com.loong.x.app.dao.SysUserDao;
import com.loong.x.app.mapper.SysUserMapper;
import com.loong.x.app.service.SysUserService;
import com.loong.x.common.custom.PaginationResult;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2024-04-22 00:45:34
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService{
    @Resource
    private SysUserDao sysUserDao;

    @Override
    public PaginationResult<SysUserDto> queryByPageWithRole(SysUserDto sysUser, PageRequest pageRequest) {
        String fullName = sysUser.getFullName();
        String phone = sysUser.getPhone();
        Integer sex = sysUser.getSex();
        Integer status = sysUser.getStatus();
        String roleName = sysUser.getRoleName();
        String username = sysUser.getUsername();
        String remark = sysUser.getRemark();
        long count = this.sysUserDao.countPage(sysUser);
        List<SysUserDto> sysUserDtos = this.sysUserDao.queryAllByLimitWithRole(fullName,phone,sex,status,roleName,username,remark, pageRequest);
        for (SysUserDto sysUserDto : sysUserDtos) {
//            System.out.println("===============================");
            System.out.println(sysUserDto.getRoleName());
//            System.out.println("===============================");
        }
        Integer totalPages = (int)count / pageRequest.getPageSize() + 1;
        return new PaginationResult<>((int) count, pageRequest.getPageNumber(), pageRequest.getPageSize(), totalPages, sysUserDtos);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(String id) {
        return this.sysUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysUser> queryByPage(SysUser sysUser, PageRequest pageRequest) {
        long total = this.sysUserDao.count(sysUser);
        return new PageImpl<>(this.sysUserDao.queryAllByLimit(sysUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserDao.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserDao.update(sysUser);
        return this.queryById(sysUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sysUserDao.deleteById(id) > 0;
    }
}
