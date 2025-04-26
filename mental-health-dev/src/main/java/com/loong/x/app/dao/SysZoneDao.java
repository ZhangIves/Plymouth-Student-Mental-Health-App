package com.loong.x.app.dao;

import com.loong.x.app.entity.SysZone;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysZone)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-24 23:52:32
 */
public interface SysZoneDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysZone queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param sysZone 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysZone> queryAllByLimit(SysZone sysZone, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysZone 查询条件
     * @return 总行数
     */
    long count(SysZone sysZone);

    /**
     * 新增数据
     *
     * @param sysZone 实例对象
     * @return 影响行数
     */
    int insert(SysZone sysZone);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysZone> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysZone> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysZone> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysZone> entities);

    /**
     * 修改数据
     *
     * @param sysZone 实例对象
     * @return 影响行数
     */
    int update(SysZone sysZone);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

