package com.loong.x.app.vo;

import lombok.Data;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/4/21 22:35
 */
@Data
public class UserVo {
    private String username;
    private String phone;

    private String password;

    private String code;

    private String key;
}
