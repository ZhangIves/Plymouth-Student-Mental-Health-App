package com.loong.x.app.dao;

import com.loong.x.app.entity.SysRepairLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysRepairLog)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-25 23:49:19
 */
public interface SysRepairLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRepairLog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysRepairLog 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysRepairLog> queryAllByLimit(SysRepairLog sysRepairLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysRepairLog 查询条件
     * @return 总行数
     */
    long count(SysRepairLog sysRepairLog);

    /**
     * 新增数据
     *
     * @param sysRepairLog 实例对象
     * @return 影响行数
     */
    int insert(SysRepairLog sysRepairLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRepairLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRepairLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRepairLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRepairLog> entities);

    /**
     * 修改数据
     *
     * @param sysRepairLog 实例对象
     * @return 影响行数
     */
    int update(SysRepairLog sysRepairLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

