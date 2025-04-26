package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loong.x.app.entity.SysRole;
import com.loong.x.app.entity.SysRolesMenus;
import com.loong.x.app.entity.User;
import com.loong.x.app.service.SysRoleService;
import com.loong.x.app.service.SysRolesMenusService;
import com.loong.x.app.vo.RoleVo;
import com.loong.x.common.utils.R;
import com.loong.x.common.utils.TimeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * (SysRole)表控制层
 *
 * @author makejava
 * @since 2024-04-22 01:05:41
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysRolesMenusService sysRolesMenusService;

    /**
     * 分页查询
     *
     * @param sysRole     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SysRole>> queryByPage(SysRole sysRole, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysRoleService.queryByPage(sysRole, pageRequest));
    }

    @GetMapping("/page")
    public R page(String roleName, String roleKey, Integer status, int page, int size) {
        LambdaQueryWrapper<SysRole> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.like(StringUtils.hasLength(roleName), SysRole::getRoleName, roleName);
        roleLambdaQueryWrapper.like(StringUtils.hasLength(roleKey), SysRole::getRoleKey, roleKey);
        if (status != null) {
            roleLambdaQueryWrapper.eq( SysRole::getStatus, status);
        }
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(page, size);
        roleLambdaQueryWrapper.orderByDesc(SysRole::getRoleSort);
        sysRoleService.page(pageInfo, roleLambdaQueryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/list")
    public R list() {
        LambdaQueryWrapper<SysRole> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.orderByDesc(SysRole::getRoleSort);
        List<SysRole> list = sysRoleService.list(roleLambdaQueryWrapper);
        return R.success(list);
    }


    @GetMapping("/menu")
    public R menu(Integer roleId) {
        LambdaQueryWrapper<SysRolesMenus> menusLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menusLambdaQueryWrapper.eq(SysRolesMenus::getRoleId, roleId);
        SysRolesMenus one = sysRolesMenusService.getOne(menusLambdaQueryWrapper);
        return R.success(one);
    }

    @PostMapping("/add")
    public R add(HttpServletRequest request, @RequestBody RoleVo sysRole) {
        System.out.println("sys - role:" + sysRole);
        LambdaQueryWrapper<SysRole> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysRole::getRoleName, sysRole.getRoleName());
        SysRole one = sysRoleService.getOne(userLambdaQueryWrapper);
        if (one == null) {
            SysRole role = sysRole.getRole();
            if (sysRoleService.save(role)) {
                SysRolesMenus rolesMenus = sysRole.getRolesMenus();
                rolesMenus.setId(TimeUtils.getTimestamp());
                rolesMenus.setRoleId(role.getId());
                sysRolesMenusService.save(rolesMenus);
            }
        } else {
            return R.error("名称已存在！");
        }
        return R.success("操作成功");
    }

    @PutMapping("edit")
    public R update(@RequestBody RoleVo sysRole) {
        SysRole role = sysRole.getRole();
        role.setId(sysRole.getId());
        if (sysRoleService.updateById(role)) {
            LambdaQueryWrapper<SysRolesMenus> menusLambdaQueryWrapper = new LambdaQueryWrapper<>();
            menusLambdaQueryWrapper.eq(SysRolesMenus::getRoleId, role.getId());
            SysRolesMenus one = sysRolesMenusService.getOne(menusLambdaQueryWrapper);
            SysRolesMenus rolesMenus = sysRole.getRolesMenus();
            rolesMenus.setId(one.getId());
            if (sysRolesMenusService.updateById(rolesMenus)) {
                return R.success("修改成功！");
            }
        }
        return R.error("操作失败");
    }

    @DeleteMapping
    public R delete(@RequestBody RoleVo sysRole) {
        this.sysRoleService.deleteById(sysRole.getId());
        LambdaQueryWrapper<SysRolesMenus> menusLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menusLambdaQueryWrapper.eq(SysRolesMenus::getRoleId, sysRole.getId());
        SysRolesMenus one = sysRolesMenusService.getOne(menusLambdaQueryWrapper);
        boolean b = sysRolesMenusService.removeById(one);
        return R.success("删除成功！");
    }

    @DeleteMapping("/list")
    public R<String> deleteLimit(@RequestBody Set<Integer> sysRole) {
        System.out.println(sysRole);
        boolean b1 = sysRoleService.removeBatchByIds(sysRole);
        for (Integer integer : sysRole) {
            LambdaQueryWrapper<SysRolesMenus> menusLambdaQueryWrapper = new LambdaQueryWrapper<>();
            menusLambdaQueryWrapper.eq(SysRolesMenus::getRoleId, integer);
            SysRolesMenus one = sysRolesMenusService.getOne(menusLambdaQueryWrapper);
            boolean b2 = sysRolesMenusService.removeById(one);
        }
        return R.success("删除成功！");
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SysRole> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.sysRoleService.queryById(id));
    }


    /**
     * 编辑数据
     *
     * @param sysRole 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<SysRole> edit(SysRole sysRole) {
        return ResponseEntity.ok(this.sysRoleService.update(sysRole));
    }


}

