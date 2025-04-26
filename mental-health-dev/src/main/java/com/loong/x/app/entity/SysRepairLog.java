package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysRepairLog)实体类
 *
 * @author makejava
 * @since 2024-04-25 23:49:21
 */
@Data
public class SysRepairLog implements Serializable {
    private static final long serialVersionUID = 825565437005143311L;

    private Long id;

    private String location;

    private String repairUser;

    private String repairUserId;

    private String phone;

    private String rapairName;

    private String remark;

    private Date reservationDate;

    private String reservationStartTime;

    private String reservationEndTime;

    private Date reservationTime;

    private Integer status;

    private String repairStatus;

    private Integer isDoor;

    private String repairImage;

    private String repairEndImage;

    private String repairEndRemark;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Date startTime;
    private Date endTime;


}

