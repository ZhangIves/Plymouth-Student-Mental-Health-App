package com.loong.x.uniapp.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/10 10:40
 */
@Data
public class EvaluationAnswerDto {

    private Integer id;

    private String answerId;
    private String userId;

    private Integer evaluationId;

    private Date createTime;

    private Date updateTime;

    /**
     * Minimum Score
     */
    private Integer scoreMin;
    /**
     * Maximum Score
     */
    private Integer scoreMax;
    /**
     * Result Level
     */
    private String resultLevel;
    /**
     * Result Description
     */
    private String resultDescription;
    /**
     * Daily Recommendations
     */
    private String dailyAdvice;
    /**
     * Professional Guidance
     */
    private String professionalAdvice;
    /**
     * Emergency Contacts
     */
    private String emergencyContact;
}
