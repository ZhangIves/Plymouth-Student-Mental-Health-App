package com.loong.x.app.dao;

import com.loong.x.app.entity.SysVisitorLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysVisitorLog)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-24 13:31:56
 */
public interface SysVisitorLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysVisitorLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param sysVisitorLog 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysVisitorLog> queryAllByLimit(SysVisitorLog sysVisitorLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysVisitorLog 查询条件
     * @return 总行数
     */
    long count(SysVisitorLog sysVisitorLog);

    /**
     * 新增数据
     *
     * @param sysVisitorLog 实例对象
     * @return 影响行数
     */
    int insert(SysVisitorLog sysVisitorLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysVisitorLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysVisitorLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysVisitorLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysVisitorLog> entities);

    /**
     * 修改数据
     *
     * @param sysVisitorLog 实例对象
     * @return 影响行数
     */
    int update(SysVisitorLog sysVisitorLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

