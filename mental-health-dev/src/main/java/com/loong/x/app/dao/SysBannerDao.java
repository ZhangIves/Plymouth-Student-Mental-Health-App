package com.loong.x.app.dao;

import com.loong.x.app.entity.SysBanner;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysBanner)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-11 10:17:20
 */
public interface SysBannerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysBanner queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysBanner 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysBanner> queryAllByLimit(SysBanner sysBanner, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysBanner 查询条件
     * @return 总行数
     */
    long count(SysBanner sysBanner);

    /**
     * 新增数据
     *
     * @param sysBanner 实例对象
     * @return 影响行数
     */
    int insert(SysBanner sysBanner);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysBanner> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysBanner> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysBanner> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysBanner> entities);

    /**
     * 修改数据
     *
     * @param sysBanner 实例对象
     * @return 影响行数
     */
    int update(SysBanner sysBanner);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

