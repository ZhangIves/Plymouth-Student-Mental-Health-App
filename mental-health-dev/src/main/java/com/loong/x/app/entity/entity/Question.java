package com.loong.x.app.entity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * (Question)实体类
 *
 * @author makejava
 * @since 2025-04-02 17:26:09
 */
@Data
public class Question implements Serializable {
    private static final long serialVersionUID = -32030642167428328L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long questionnaireId;

    private String content;

    private String type;

    private Integer level;


}

