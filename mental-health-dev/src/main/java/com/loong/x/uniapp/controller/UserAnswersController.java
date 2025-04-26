package com.loong.x.uniapp.controller;

import com.loong.x.app.entity.entity.Answer;
import com.loong.x.app.entity.entity.QuestionOption;
import com.loong.x.app.entity.entity.Questionnaire;
import com.loong.x.app.service.AnswerService;
import com.loong.x.app.service.QuestionnaireService;
import com.loong.x.uniapp.dto.UserAnswerDTO;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 18:39
 */
public class UserAnswersController extends BaseController {
    // 1. 新增答题记录查询接口‌:ml-citation{ref="6" data="citationList"}
    @Resource
    private AnswerService answerService;

    @Resource
    private QuestionnaireService questionnaireService;



//    private Map<Long, Object> getAnswerDetails(Answer a) {
//        return a.getSelectedOptions().stream()
//                .collect(Collectors.toMap(
//                        opt -> opt.getQuestionId(),  // 确保QuestionOption有questionId字段
//                        opt -> {
//                            if ("TEXT".equals(opt.getType())) {
//                                return Optional.ofNullable(opt.getAnswerText())
//                                        .orElse("无文本回答");
//                            }
//                            return Optional.ofNullable(opt.getSelectedOptions())
//                                    .orElse(Collections.emptyList());
//                        }));
//    }
//
//    @GetMapping("/user/answers")
//    public ResponseObject getUserAnswers(@RequestHeader("Authorization") String token) {
//        // 2. 通过Token获取当前用户ID‌:ml-citation{ref="7" data="citationList"}
////        Long userId = authService.getUserIdFromToken(token);
//
//        // 3. 查询用户所有答题记录‌:ml-citation{ref="5" data="citationList"}
//        List<Answer> answers = answerService.lambdaQuery()
//                .eq(Answer::getUserId, token)
//                .orderByDesc(Answer::getCreateTime)
//                .list();
//
//        // 4. 转换为DTO并关联问卷信息‌:ml-citation{ref="6" data="citationList"}
//        List<UserAnswerDTO> result = answers.stream().map(a -> {
//            Questionnaire questionnaire = questionnaireService.getById(a.getQuestionnaireId());
//            return new UserAnswerDTO(
//                    a.getId(),
//                    questionnaire.getTitle(),
//                    a.getCreateTime(),
//                    getAnswerDetails(a) // 获取详细答题内容
//            );
//        }).collect(Collectors.toList());
//
//        return getSuccessResult(result);
//    }

//    // 5. 获取答案详情方法
//    private Map<Long, Object> getAnswerDetails(Answer answer) {
//        return answer.getSelectedOptions().stream()
//                .collect(Collectors.toMap(
//                        QuestionOption::getQuestionId,
//                        opt -> "TEXT".equals(opt.getType()) ?
//                                opt.getAnswerText() :
//                                opt.getSelectedOptions()
//                ));
//    }

}
