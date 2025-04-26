package com.loong.x.chat.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: hdx
 * @Date: 2022-09-06 15:14
 * @version: 1.0
 **/
@Data
public class RetrieveVo implements Serializable {

    /**
     * 手机号
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;
}
