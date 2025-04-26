package com.loong.x.app.entity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Questionnaire)实体类
 *
 * @author makejava
 * @since 2025-04-02 17:26:09
 */
@Data
public class Questionnaire implements Serializable {
    private static final long serialVersionUID = -35259009004487833L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String description;

    private Date createTime;

}

