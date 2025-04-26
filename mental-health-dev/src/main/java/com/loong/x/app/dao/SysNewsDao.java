package com.loong.x.app.dao;

import com.loong.x.app.entity.SysNews;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysNews)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-11 14:07:09
 */
public interface SysNewsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysNews queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param sysNews 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysNews> queryAllByLimit(SysNews sysNews, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysNews 查询条件
     * @return 总行数
     */
    long count(SysNews sysNews);

    /**
     * 新增数据
     *
     * @param sysNews 实例对象
     * @return 影响行数
     */
    int insert(SysNews sysNews);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNews> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysNews> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNews> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysNews> entities);

    /**
     * 修改数据
     *
     * @param sysNews 实例对象
     * @return 影响行数
     */
    int update(SysNews sysNews);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

