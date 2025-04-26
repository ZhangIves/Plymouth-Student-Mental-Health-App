package com.loong.x.app.dao;

import com.loong.x.app.entity.SysRolesMenus;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 角色菜单关联(SysRolesMenus)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-22 01:47:23
 */
public interface SysRolesMenusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRolesMenus queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param sysRolesMenus 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysRolesMenus> queryAllByLimit(SysRolesMenus sysRolesMenus, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysRolesMenus 查询条件
     * @return 总行数
     */
    long count(SysRolesMenus sysRolesMenus);

    /**
     * 新增数据
     *
     * @param sysRolesMenus 实例对象
     * @return 影响行数
     */
    int insert(SysRolesMenus sysRolesMenus);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRolesMenus> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRolesMenus> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRolesMenus> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRolesMenus> entities);

    /**
     * 修改数据
     *
     * @param sysRolesMenus 实例对象
     * @return 影响行数
     */
    int update(SysRolesMenus sysRolesMenus);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

