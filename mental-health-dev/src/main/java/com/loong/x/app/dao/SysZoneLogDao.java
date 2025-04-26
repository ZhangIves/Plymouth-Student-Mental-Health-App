package com.loong.x.app.dao;

import com.loong.x.app.entity.SysZoneLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysZoneLog)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-25 13:20:11
 */
public interface SysZoneLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysZoneLog queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param sysZoneLog 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysZoneLog> queryAllByLimit(SysZoneLog sysZoneLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysZoneLog 查询条件
     * @return 总行数
     */
    long count(SysZoneLog sysZoneLog);

    /**
     * 新增数据
     *
     * @param sysZoneLog 实例对象
     * @return 影响行数
     */
    int insert(SysZoneLog sysZoneLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysZoneLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysZoneLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysZoneLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysZoneLog> entities);

    /**
     * 修改数据
     *
     * @param sysZoneLog 实例对象
     * @return 影响行数
     */
    int update(SysZoneLog sysZoneLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

