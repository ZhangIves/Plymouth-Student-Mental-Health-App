package com.loong.x.app.entity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * (QuestionOption)实体类
 *
 * @author makejava
 * @since 2025-04-02 17:26:09
 */
@Data
public class QuestionOption implements Serializable {
    private static final long serialVersionUID = 671395817257271421L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long questionId;

    private String content;

    private Integer level;

    private Integer score;


}

