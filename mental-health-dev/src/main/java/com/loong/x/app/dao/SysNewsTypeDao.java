package com.loong.x.app.dao;

import com.loong.x.app.entity.SysNewsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysNewsType)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-10 23:49:59
 */
public interface SysNewsTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysNewsType queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param sysNewsType 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysNewsType> queryAllByLimit(SysNewsType sysNewsType, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysNewsType 查询条件
     * @return 总行数
     */
    long count(SysNewsType sysNewsType);

    /**
     * 新增数据
     *
     * @param sysNewsType 实例对象
     * @return 影响行数
     */
    int insert(SysNewsType sysNewsType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNewsType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysNewsType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNewsType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysNewsType> entities);

    /**
     * 修改数据
     *
     * @param sysNewsType 实例对象
     * @return 影响行数
     */
    int update(SysNewsType sysNewsType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

