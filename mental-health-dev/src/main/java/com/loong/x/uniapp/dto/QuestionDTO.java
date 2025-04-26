package com.loong.x.uniapp.dto;

import com.loong.x.app.entity.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

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
public class QuestionDTO {
    private Long id;
    private String content;
    private String type;
    private List<OptionDTO> options;
    // 需要显式声明构造方法
    public QuestionDTO(Question question, List<OptionDTO> options) {
        this.id = question.getId();
        this.content = question.getContent();
        this.type = question.getType();
        this.options = options;
    }

}
