package com.loong.x.app.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysQuestionLog)实体类
 *
 * @author makejava
 * @since 2024-05-12 17:19:26
 */
public class SysQuestionLog implements Serializable {
    private static final long serialVersionUID = -99789314009004257L;

    private Integer id;

    private Integer questionId;

    private String userId;

    private String content;

    private Date createTime;

    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}

