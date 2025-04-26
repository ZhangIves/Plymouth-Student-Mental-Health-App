package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.QuestionAnswer;
import com.loong.x.app.dao.QuestionAnswerDao;
import com.loong.x.app.service.QuestionAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (QuestionAnswer)表服务实现类
 *
 * @author makejava
 * @since 2025-04-08 11:07:29
 */
@Service("questionAnswerService")
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerDao,QuestionAnswer> implements QuestionAnswerService {
    @Resource
    private QuestionAnswerDao questionAnswerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public QuestionAnswer queryById(Integer id) {
        return this.questionAnswerDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param questionAnswer 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<QuestionAnswer> queryByPage(QuestionAnswer questionAnswer, PageRequest pageRequest) {
        long total = this.questionAnswerDao.count(questionAnswer);
        return new PageImpl<>(this.questionAnswerDao.queryAllByLimit(questionAnswer, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param questionAnswer 实例对象
     * @return 实例对象
     */
    @Override
    public QuestionAnswer insert(QuestionAnswer questionAnswer) {
        this.questionAnswerDao.insert(questionAnswer);
        return questionAnswer;
    }

    /**
     * 修改数据
     *
     * @param questionAnswer 实例对象
     * @return 实例对象
     */
    @Override
    public QuestionAnswer update(QuestionAnswer questionAnswer) {
        this.questionAnswerDao.update(questionAnswer);
        return this.queryById(questionAnswer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.questionAnswerDao.deleteById(id) > 0;
    }
}
