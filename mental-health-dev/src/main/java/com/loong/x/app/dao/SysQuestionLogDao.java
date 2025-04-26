package com.loong.x.app.dao;

import com.loong.x.app.entity.SysQuestionLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysQuestionLog)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-12 17:19:26
 */
public interface SysQuestionLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysQuestionLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param sysQuestionLog 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysQuestionLog> queryAllByLimit(SysQuestionLog sysQuestionLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysQuestionLog 查询条件
     * @return 总行数
     */
    long count(SysQuestionLog sysQuestionLog);

    /**
     * 新增数据
     *
     * @param sysQuestionLog 实例对象
     * @return 影响行数
     */
    int insert(SysQuestionLog sysQuestionLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysQuestionLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysQuestionLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysQuestionLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysQuestionLog> entities);

    /**
     * 修改数据
     *
     * @param sysQuestionLog 实例对象
     * @return 影响行数
     */
    int update(SysQuestionLog sysQuestionLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

