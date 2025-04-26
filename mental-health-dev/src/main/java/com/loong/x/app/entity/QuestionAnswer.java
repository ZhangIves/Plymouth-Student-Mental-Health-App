package com.loong.x.app.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (QuestionAnswer)实体类
 *
 * @author makejava
 * @since 2025-04-08 11:07:28
 */
@Data
public class QuestionAnswer implements Serializable {
    private static final long serialVersionUID = 508133060014535778L;

    private Integer id;

    private Integer evaluationId;

    private Date createTime;

    private Date updateTime;

    private String remark;


}

