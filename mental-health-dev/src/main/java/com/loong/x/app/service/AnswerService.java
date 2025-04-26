package com.loong.x.app.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.entity.Answer;
import com.loong.x.app.mapper.AnswerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 17:32
 */
@Service
public class AnswerService extends ServiceImpl<AnswerMapper,Answer> {
    @Resource
    private AnswerMapper answerMapper;

    @Transactional
    public void saveAnswers(List<Answer> answers) {
        answers.forEach(answerMapper::insert);
    }
}
