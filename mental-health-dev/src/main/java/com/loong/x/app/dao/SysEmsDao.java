package com.loong.x.app.dao;

import com.loong.x.app.entity.SysEms;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysEms)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-26 15:49:36
 */
public interface SysEmsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysEms queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param sysEms 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysEms> queryAllByLimit(SysEms sysEms, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysEms 查询条件
     * @return 总行数
     */
    long count(SysEms sysEms);

    /**
     * 新增数据
     *
     * @param sysEms 实例对象
     * @return 影响行数
     */
    int insert(SysEms sysEms);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysEms> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysEms> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysEms> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysEms> entities);

    /**
     * 修改数据
     *
     * @param sysEms 实例对象
     * @return 影响行数
     */
    int update(SysEms sysEms);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

