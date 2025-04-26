package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.app.entity.QuestionAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (QuestionAnswer)表服务接口
 *
 * @author makejava
 * @since 2025-04-08 11:07:28
 */
public interface QuestionAnswerService extends IService<QuestionAnswer> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    QuestionAnswer queryById(Integer id);

    /**
     * 分页查询
     *
     * @param questionAnswer 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<QuestionAnswer> queryByPage(QuestionAnswer questionAnswer, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param questionAnswer 实例对象
     * @return 实例对象
     */
    QuestionAnswer insert(QuestionAnswer questionAnswer);

    /**
     * 修改数据
     *
     * @param questionAnswer 实例对象
     * @return 实例对象
     */
    QuestionAnswer update(QuestionAnswer questionAnswer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
