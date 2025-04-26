package com.loong.x.uniapp.controller;

import com.loong.x.app.entity.EvaluationAnswer;
import com.loong.x.app.entity.MentalHealthEvaluation;
import com.loong.x.app.entity.entity.Answer;
import com.loong.x.app.service.AnswerService;
import com.loong.x.app.service.EvaluationAnswerService;
import com.loong.x.app.service.MentalHealthEvaluationService;
import com.loong.x.common.utils.TimeUtils;
import com.loong.x.uniapp.dto.AnswerDTO;
import com.loong.x.uniapp.dto.AnswerSubmitDTO;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 17:41
 */
@RestController
@RequestMapping("/clientApi/answer")
public class AnswerController extends BaseController {
    @Resource
    private AnswerService answerService;
    @Resource
    private MentalHealthEvaluationService mentalHealthEvaluationService;
    @Resource
    private EvaluationAnswerService evaluationAnswerService;

    @PostMapping("/submit")
    public ResponseObject submitAnswers(HttpServletRequest request, @RequestBody AnswerSubmitDTO dto) {
        String timestamp = TimeUtils.getTimestamp();
        System.out.println("================");
        String token = dto.getUserId();
        System.out.println(token);
        System.out.println("================");
        if (token.equals("")) {
            return getFailureResult(1001,"please log in first");
        }
        AtomicReference<Integer> totalScore = new AtomicReference<>(0);
        List<AnswerDTO> answers1 = dto.getAnswers();
        for (AnswerDTO answerDTO : answers1) {
            if (answerDTO.getSelectedOptions().isEmpty()){
                return getFailureResult(1001,"Please fill out the questionnaire completely");
            }
        }
        List<Answer> answers = dto.getAnswers().stream().map(a -> {
            Answer answer = new Answer();
            answer.setUserId(dto.getUserId());
            answer.setQuestionnaireId(dto.getQuestionnaireId());
            answer.setQuestionId(a.getQuestionId());
            answer.setUuid(timestamp);
            if ("TEXT".equals(a.getType())) {
                answer.setAnswerText(a.getAnswerText());
            } else {
                // 注意：建议用JSON序列化代替toString()
                answer.setSelectedOptions(a.getSelectedOptions().get(0).toString());
                answer.setScore(a.getScore());
                totalScore.set(totalScore.get() + a.getScore());

            }
            return answer;
        }).collect(Collectors.toList());

        answerService.saveAnswers(answers);

        List<MentalHealthEvaluation> list = mentalHealthEvaluationService.list();
        for (MentalHealthEvaluation mentalHealthEvaluation : list) {
            if (totalScore.get() > mentalHealthEvaluation.getScoreMin() && totalScore.get() < mentalHealthEvaluation.getScoreMax()){
                EvaluationAnswer evaluationAnswer = new EvaluationAnswer();
                evaluationAnswer.setAnswerId(timestamp);
                evaluationAnswer.setUserId(token);
                evaluationAnswer.setEvaluationId(mentalHealthEvaluation.getId());
                evaluationAnswerService.saveOrUpdate(evaluationAnswer);
            }
        }


        return getSuccessResult("提交成功");
    }

}
