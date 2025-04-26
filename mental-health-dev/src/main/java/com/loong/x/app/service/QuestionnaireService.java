package com.loong.x.app.service;

import com.loong.x.app.entity.entity.Questionnaire;
import com.loong.x.app.mapper.QuestionnaireMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 17:29
 */

// QuestionnaireService.java
@Service
public class QuestionnaireService {
    @Resource
    private QuestionnaireMapper questionnaireMapper;

    public Questionnaire getById(Long id) {
        return questionnaireMapper.selectById(id);
    }
}
