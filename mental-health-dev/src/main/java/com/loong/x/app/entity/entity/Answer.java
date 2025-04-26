package com.loong.x.app.entity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Answer)实体类
 *
 * @author makejava
 * @since 2025-04-02 17:26:08
 */
@Data
public class Answer implements Serializable {
    private static final long serialVersionUID = 840160123278717619L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户标识
     */
    private String userId;

    private String uuid;

    private Long questionnaireId;

    private Long questionId;
    /**
     * 选中的选项ID列表
     */
//    private List<Long> selectedOptions;
    private String selectedOptions;
//    @TableField(typeHandler = JsonTypeHandler.class)
//    private List<QuestionOption> selectedOptions;
    /**
     * 文本回答
     */
    private String answerText;

    private Date createTime;

    private Integer score;

}

