package com.loong.x.app.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Mental Health Evaluation Scoring Criteria(MentalHealthEvaluation)实体类
 *
 * @author makejava
 * @since 2025-04-08 11:07:29
 */
@Data
public class MentalHealthEvaluation implements Serializable {
    private static final long serialVersionUID = -78139949609082399L;
    /**
     * Primary Key ID
     */
    private Integer id;
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

