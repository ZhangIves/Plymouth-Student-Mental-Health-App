package com.loong.x.chat.model.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * @Description: 登录信息Vo
 * @Author: hdx
 * @Date: 2022/1/29 15:38
 * @Version: 1.0
 */
@Data
public class LoginVo implements Serializable {

    /**
     * 手机号
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
