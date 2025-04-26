package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.dto.SysUserDto;
import com.loong.x.app.entity.EvaluationAnswer;
import com.loong.x.app.dao.EvaluationAnswerDao;
import com.loong.x.app.service.EvaluationAnswerService;
import com.loong.x.common.custom.PaginationResult;
import com.loong.x.uniapp.dto.EvaluationAnswerDto;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (EvaluationAnswer)表服务实现类
 *
 * @author makejava
 * @since 2025-04-10 10:33:22
 */
@Service("evaluationAnswerService")
public class EvaluationAnswerServiceImpl extends ServiceImpl<EvaluationAnswerDao,EvaluationAnswer> implements EvaluationAnswerService {
    @Resource
    private EvaluationAnswerDao evaluationAnswerDao;

    @Override
    public PaginationResult<EvaluationAnswerDto> queryAllByPageLimit(EvaluationAnswer evaluationAnswer,PageRequest pageRequest) {
        long count = this.evaluationAnswerDao.count(evaluationAnswer);
        List<EvaluationAnswerDto> evaluationAnswerDtos = this.evaluationAnswerDao.queryAllByPageLimit(evaluationAnswer, pageRequest);
        Integer totalPages = (int)count / pageRequest.getPageSize() + 1;
        return new PaginationResult<>((int) count, pageRequest.getPageNumber(), pageRequest.getPageSize(), totalPages, evaluationAnswerDtos);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public EvaluationAnswer queryById(Integer id) {
        return this.evaluationAnswerDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param evaluationAnswer 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<EvaluationAnswer> queryByPage(EvaluationAnswer evaluationAnswer, PageRequest pageRequest) {
        long total = this.evaluationAnswerDao.count(evaluationAnswer);
        return new PageImpl<>(this.evaluationAnswerDao.queryAllByLimit(evaluationAnswer, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param evaluationAnswer 实例对象
     * @return 实例对象
     */
    @Override
    public EvaluationAnswer insert(EvaluationAnswer evaluationAnswer) {
        this.evaluationAnswerDao.insert(evaluationAnswer);
        return evaluationAnswer;
    }

    /**
     * 修改数据
     *
     * @param evaluationAnswer 实例对象
     * @return 实例对象
     */
    @Override
    public EvaluationAnswer update(EvaluationAnswer evaluationAnswer) {
        this.evaluationAnswerDao.update(evaluationAnswer);
        return this.queryById(evaluationAnswer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.evaluationAnswerDao.deleteById(id) > 0;
    }
}
