package com.loong.x.app.vo;

import com.loong.x.app.entity.SysRole;
import com.loong.x.app.entity.SysRolesMenus;
import lombok.Data;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/4/23 10:22
 */
@Data
public class RoleVo {

    private Integer id;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private Integer status;

    private String remark;

    public SysRole getRole(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(this.roleName);
        sysRole.setRoleKey(this.roleKey);
        sysRole.setRoleSort(this.roleSort);
        sysRole.setStatus(this.status);
        sysRole.setRemark(this.remark);
        return sysRole;
    }

    private SysRolesMenus rolesMenus;

}
