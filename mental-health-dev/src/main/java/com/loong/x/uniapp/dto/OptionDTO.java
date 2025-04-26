package com.loong.x.uniapp.dto;

import com.loong.x.app.entity.entity.QuestionOption;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 17:37
 */
@Data
@AllArgsConstructor
public class OptionDTO {
    private Long id;
    private String content;
    private Integer score;
    // 新增专门处理QuestionOption的构造方法
    public OptionDTO(QuestionOption questionOption) {
        this.id = questionOption.getId();
        this.content = questionOption.getContent();
        this.score = questionOption.getScore();
    }
}
