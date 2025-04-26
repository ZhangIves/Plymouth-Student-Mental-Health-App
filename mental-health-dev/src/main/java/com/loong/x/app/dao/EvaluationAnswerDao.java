package com.loong.x.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loong.x.app.dto.SysUserDto;
import com.loong.x.app.entity.EvaluationAnswer;
import com.loong.x.uniapp.dto.EvaluationAnswerDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (EvaluationAnswer)表数据库访问层
 *
 * @author makejava
 * @since 2025-04-10 10:33:22
 */
@Mapper
public interface EvaluationAnswerDao extends BaseMapper<EvaluationAnswer> {

    List<EvaluationAnswerDto> queryAllByPageLimit(EvaluationAnswer evaluationAnswer, @Param("pageable") Pageable pageable);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EvaluationAnswer queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param evaluationAnswer 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<EvaluationAnswer> queryAllByLimit(EvaluationAnswer evaluationAnswer, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param evaluationAnswer 查询条件
     * @return 总行数
     */
    long count(EvaluationAnswer evaluationAnswer);

    /**
     * 新增数据
     *
     * @param evaluationAnswer 实例对象
     * @return 影响行数
     */
    int insert(EvaluationAnswer evaluationAnswer);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<EvaluationAnswer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<EvaluationAnswer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<EvaluationAnswer> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<EvaluationAnswer> entities);

    /**
     * 修改数据
     *
     * @param evaluationAnswer 实例对象
     * @return 影响行数
     */
    int update(EvaluationAnswer evaluationAnswer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

