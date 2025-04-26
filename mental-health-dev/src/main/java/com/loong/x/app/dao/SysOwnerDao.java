package com.loong.x.app.dao;

import com.loong.x.app.entity.SysOwner;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysOwner)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-24 15:45:37
 */
public interface SysOwnerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysOwner queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysOwner 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysOwner> queryAllByLimit(SysOwner sysOwner, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysOwner 查询条件
     * @return 总行数
     */
    long count(SysOwner sysOwner);

    /**
     * 新增数据
     *
     * @param sysOwner 实例对象
     * @return 影响行数
     */
    int insert(SysOwner sysOwner);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysOwner> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysOwner> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysOwner> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysOwner> entities);

    /**
     * 修改数据
     *
     * @param sysOwner 实例对象
     * @return 影响行数
     */
    int update(SysOwner sysOwner);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

