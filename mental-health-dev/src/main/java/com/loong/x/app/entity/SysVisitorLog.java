package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (SysVisitorLog)实体类
 *
 * @author makejava
 * @since 2024-04-24 13:21:01
 */
@Data
public class SysVisitorLog implements Serializable {
    private static final long serialVersionUID = -91693725776610975L;

    @TableId
    private Integer id;

    private String visitorName;

    private Integer gender;

    private Integer age;
    private Integer status;

    private String idNumber;

    private String idType;

    private String contactNumber;

    private Date visitDate;

    private LocalTime visitTime;

    private String visitPurpose;

    private String visitedDepartment;

        private String receptionist;

    private String remarks;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Date startTime;
    private Date endTime;

}

