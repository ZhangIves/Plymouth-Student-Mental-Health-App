package com.loong.x.app.dto;

import com.loong.x.app.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/4/22 0:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginUserDto extends SysUser {
    private String roleName;


}
