package com.loong.x.uniapp.dto;

import com.loong.x.app.entity.entity.Questionnaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 17:36
 */
@Data
@AllArgsConstructor
@ToString
public class QuestionnaireDTO {
    private Long id;
    private String title;
    private String description;
    private List<QuestionDTO> questions;
    // 必须显式声明此构造方法
    public QuestionnaireDTO(Questionnaire questionnaire, List<QuestionDTO> questions) {
        this.id = questionnaire.getId();
        this.title = questionnaire.getTitle();
        this.description = questionnaire.getDescription();
        this.questions = questions;
    }
}
