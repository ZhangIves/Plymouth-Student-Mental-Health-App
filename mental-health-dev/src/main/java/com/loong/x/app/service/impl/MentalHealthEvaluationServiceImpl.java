package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.MentalHealthEvaluation;
import com.loong.x.app.dao.MentalHealthEvaluationDao;
import com.loong.x.app.service.MentalHealthEvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * Mental Health Evaluation Scoring Criteria(MentalHealthEvaluation)表服务实现类
 *
 * @author makejava
 * @since 2025-04-08 11:07:29
 */
@Service("mentalHealthEvaluationService")
public class MentalHealthEvaluationServiceImpl extends ServiceImpl<MentalHealthEvaluationDao,MentalHealthEvaluation> implements MentalHealthEvaluationService {
    @Resource
    private MentalHealthEvaluationDao mentalHealthEvaluationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MentalHealthEvaluation queryById(Integer id) {
        return this.mentalHealthEvaluationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param mentalHealthEvaluation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<MentalHealthEvaluation> queryByPage(MentalHealthEvaluation mentalHealthEvaluation, PageRequest pageRequest) {
        long total = this.mentalHealthEvaluationDao.count(mentalHealthEvaluation);
        return new PageImpl<>(this.mentalHealthEvaluationDao.queryAllByLimit(mentalHealthEvaluation, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param mentalHealthEvaluation 实例对象
     * @return 实例对象
     */
    @Override
    public MentalHealthEvaluation insert(MentalHealthEvaluation mentalHealthEvaluation) {
        this.mentalHealthEvaluationDao.insert(mentalHealthEvaluation);
        return mentalHealthEvaluation;
    }

    /**
     * 修改数据
     *
     * @param mentalHealthEvaluation 实例对象
     * @return 实例对象
     */
    @Override
    public MentalHealthEvaluation update(MentalHealthEvaluation mentalHealthEvaluation) {
        this.mentalHealthEvaluationDao.update(mentalHealthEvaluation);
        return this.queryById(mentalHealthEvaluation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.mentalHealthEvaluationDao.deleteById(id) > 0;
    }
}
