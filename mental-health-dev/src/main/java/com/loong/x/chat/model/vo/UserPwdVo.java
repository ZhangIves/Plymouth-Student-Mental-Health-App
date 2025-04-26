package com.loong.x.chat.model.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * @Description: 用户密码Vo
 * @Author: hdx
 * @Date: 2022/1/29 15:38
 * @Version: 1.0
 */
@Data
public class UserPwdVo implements Serializable {

    /**
     * 老密码
     */
    private String oldPwd;

    /**
     * 新密码
     */
    private String newPwd;

    /**
     * 确认密码
     */
    private String comfirmPwd;

}
