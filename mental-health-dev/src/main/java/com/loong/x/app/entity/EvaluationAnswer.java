package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (EvaluationAnswer)实体类
 *
 * @author makejava
 * @since 2025-04-10 10:33:22
 */
@Data
public class EvaluationAnswer implements Serializable {
    private static final long serialVersionUID = 307614299561568164L;

    private Integer id;

    private String answerId;

    private Integer evaluationId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String userId;



}

