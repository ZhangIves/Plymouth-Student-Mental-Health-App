package com.loong.x.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loong.x.app.entity.entity.QuestionOption;
import com.loong.x.app.mapper.QuestionOptionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 17:31
 */
@Service
public class QuestionOptionService {
    @Resource
    private QuestionOptionMapper optionMapper;

    public List<QuestionOption> getOptionsByQuestionId(Long questionId) {
        return optionMapper.selectList(new QueryWrapper<QuestionOption>()
                .eq("question_id", questionId)
                .orderByAsc("level"));
    }
}
