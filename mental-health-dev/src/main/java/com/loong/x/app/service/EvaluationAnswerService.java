package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.app.dto.SysUserDto;
import com.loong.x.app.entity.EvaluationAnswer;
import com.loong.x.common.custom.PaginationResult;
import com.loong.x.uniapp.dto.EvaluationAnswerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (EvaluationAnswer)表服务接口
 *
 * @author makejava
 * @since 2025-04-10 10:33:22
 */
public interface EvaluationAnswerService extends IService<EvaluationAnswer> {

    PaginationResult<EvaluationAnswerDto> queryAllByPageLimit(EvaluationAnswer evaluationAnswer,PageRequest pageRequest);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EvaluationAnswer queryById(Integer id);

    /**
     * 分页查询
     *
     * @param evaluationAnswer 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<EvaluationAnswer> queryByPage(EvaluationAnswer evaluationAnswer, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param evaluationAnswer 实例对象
     * @return 实例对象
     */
    EvaluationAnswer insert(EvaluationAnswer evaluationAnswer);

    /**
     * 修改数据
     *
     * @param evaluationAnswer 实例对象
     * @return 实例对象
     */
    EvaluationAnswer update(EvaluationAnswer evaluationAnswer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
