package com.loong.x.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loong.x.app.entity.entity.Question;
import com.loong.x.app.mapper.QuestionMapper;
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
public class QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    public List<Question> getQuestionsByQuestionnaireId(Long questionnaireId) {
        return questionMapper.selectList(new QueryWrapper<Question>()
                .eq("questionnaire_id", questionnaireId)
                .orderByAsc("level"));
    }
}
