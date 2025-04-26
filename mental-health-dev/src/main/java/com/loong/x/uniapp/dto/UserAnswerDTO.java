package com.loong.x.uniapp.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 18:37
 */

@Data
public class UserAnswerDTO {
    private Long answerId;
    private String questionnaireTitle;
    private Date submitTime;
    private Map<Long, Object> answerDetails; // Key:问题ID, Value:答案内容
}
