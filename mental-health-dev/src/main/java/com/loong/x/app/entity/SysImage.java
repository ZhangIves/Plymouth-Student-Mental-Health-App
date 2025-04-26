package com.loong.x.app.entity;

import java.io.Serializable;

/**
 * (SysImage)实体类
 *
 * @author makejava
 * @since 2024-04-24 15:10:35
 */
public class SysImage implements Serializable {
    private static final long serialVersionUID = -41123963991058640L;

    private Long id;

    private String repairId;

    private String complaintId;

    private String imageUrl;

    public SysImage(String repairId, String imageUrl) {
        this.repairId = repairId;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}

