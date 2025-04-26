package com.loong.x.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loong.x.app.entity.QuestionAnswer;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (QuestionAnswer)表数据库访问层
 *
 * @author makejava
 * @since 2025-04-08 11:07:28
 */
public interface QuestionAnswerDao extends BaseMapper<QuestionAnswer> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    QuestionAnswer queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param questionAnswer 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<QuestionAnswer> queryAllByLimit(QuestionAnswer questionAnswer, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param questionAnswer 查询条件
     * @return 总行数
     */
    long count(QuestionAnswer questionAnswer);

    /**
     * 新增数据
     *
     * @param questionAnswer 实例对象
     * @return 影响行数
     */
    int insert(QuestionAnswer questionAnswer);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<QuestionAnswer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<QuestionAnswer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<QuestionAnswer> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<QuestionAnswer> entities);

    /**
     * 修改数据
     *
     * @param questionAnswer 实例对象
     * @return 影响行数
     */
    int update(QuestionAnswer questionAnswer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

