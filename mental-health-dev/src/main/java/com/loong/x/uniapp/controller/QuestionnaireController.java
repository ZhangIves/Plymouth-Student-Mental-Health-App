package com.loong.x.uniapp.controller;

import com.loong.x.app.entity.entity.Question;
import com.loong.x.app.entity.entity.QuestionOption;
import com.loong.x.app.entity.entity.Questionnaire;
import com.loong.x.app.service.QuestionOptionService;
import com.loong.x.app.service.QuestionService;
import com.loong.x.app.service.QuestionnaireService;
import com.loong.x.uniapp.dto.OptionDTO;
import com.loong.x.uniapp.dto.QuestionDTO;
import com.loong.x.uniapp.dto.QuestionnaireDTO;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 17:32
 */
@RestController
@RequestMapping("/clientApi/questionnaire")
public class QuestionnaireController extends BaseController {
    @Resource
    private QuestionnaireService questionnaireService;
    @Resource
    private QuestionService questionService;
    @Resource
    private QuestionOptionService optionService;

    @GetMapping("/{id}")
    public ResponseObject getQuestionnaire(@PathVariable Long id) {
        Questionnaire questionnaire = questionnaireService.getById(id);
        if (questionnaire == null) return getFailureResult(0, "问卷不存在");

        List<Question> questions = questionService.getQuestionsByQuestionnaireId(id);

        List<QuestionDTO> questionDTOs = questions.stream()
                .map(q -> {
                    // 获取关联选项
                    List<QuestionOption> options = optionService.getOptionsByQuestionId(q.getId());

                    // 转换OptionDTO列表
                    List<OptionDTO> optionDTOs = options.stream()
                            .map(OptionDTO::new)  // 使用新增的构造方法
                            .collect(Collectors.toList());

                    // 创建QuestionDTO
                    return new QuestionDTO(q, optionDTOs);
                })
                .collect(Collectors.toList());
        QuestionnaireDTO questionnaireDTO = new QuestionnaireDTO(questionnaire, questionDTOs);
        System.out.println("==================================");
        System.out.println(questionnaireDTO);
        System.out.println("==================================");

        return getSuccessResult(questionnaireDTO);
    }

}
