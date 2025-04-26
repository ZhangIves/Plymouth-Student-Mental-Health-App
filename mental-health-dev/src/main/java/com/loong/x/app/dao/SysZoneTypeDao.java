package com.loong.x.app.dao;

import com.loong.x.app.entity.SysZoneType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysZoneType)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-10 23:49:46
 */
public interface SysZoneTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysZoneType queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param sysZoneType 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysZoneType> queryAllByLimit(SysZoneType sysZoneType, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysZoneType 查询条件
     * @return 总行数
     */
    long count(SysZoneType sysZoneType);

    /**
     * 新增数据
     *
     * @param sysZoneType 实例对象
     * @return 影响行数
     */
    int insert(SysZoneType sysZoneType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysZoneType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysZoneType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysZoneType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysZoneType> entities);

    /**
     * 修改数据
     *
     * @param sysZoneType 实例对象
     * @return 影响行数
     */
    int update(SysZoneType sysZoneType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

