package com.loong.x.uniapp.dto;

import lombok.Data;

import java.util.List;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 17:37
 */
@Data
public class AnswerDTO {
    private Long questionId;
    private String type;
    private List<Long> selectedOptions;
    private String answerText;
    private Integer score;
}
