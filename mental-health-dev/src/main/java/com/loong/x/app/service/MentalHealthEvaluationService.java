package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.app.entity.MentalHealthEvaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Mental Health Evaluation Scoring Criteria(MentalHealthEvaluation)表服务接口
 *
 * @author makejava
 * @since 2025-04-08 11:07:29
 */
public interface MentalHealthEvaluationService extends IService<MentalHealthEvaluation> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MentalHealthEvaluation queryById(Integer id);

    /**
     * 分页查询
     *
     * @param mentalHealthEvaluation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<MentalHealthEvaluation> queryByPage(MentalHealthEvaluation mentalHealthEvaluation, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param mentalHealthEvaluation 实例对象
     * @return 实例对象
     */
    MentalHealthEvaluation insert(MentalHealthEvaluation mentalHealthEvaluation);

    /**
     * 修改数据
     *
     * @param mentalHealthEvaluation 实例对象
     * @return 实例对象
     */
    MentalHealthEvaluation update(MentalHealthEvaluation mentalHealthEvaluation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
