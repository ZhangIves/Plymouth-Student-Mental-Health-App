package com.loong.x.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loong.x.app.dto.SysUserDto;
import com.loong.x.app.entity.SysRole;
import com.loong.x.app.entity.SysUser;
import com.loong.x.app.entity.SysUserRole;
import com.loong.x.app.entity.User;
import com.loong.x.app.service.SysRoleService;
import com.loong.x.app.service.SysUserRoleService;
import com.loong.x.app.service.SysUserService;
import com.loong.x.app.vo.UserVo;
import com.loong.x.common.config.CaptchaConfig;
import com.loong.x.common.utils.R;
import com.loong.x.common.utils.TimeUtils;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * (SysSysUser)表控制层
 *
 * @author makejava
 * @since 2024-04-22 00:45:34
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/login")
    public R<SysUser> login(HttpServletRequest request, @RequestBody UserVo user) {
        String key = user.getKey();
        // 判断验证码正误
        if (!user.getCode().toLowerCase().equals(CaptchaConfig.CAPTCHA_MAP.get(key))) {
            CaptchaUtil.clear(request);
            CaptchaConfig.CAPTCHA_MAP.remove(key);
            return R.error("验证码错误");
        }
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<SysUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysUser::getUsername, user.getUsername());
        SysUser usr = sysUserService.getOne(userLambdaQueryWrapper);
        if (usr == null) {
            LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SysUser::getPhone, user.getUsername());
            usr = sysUserService.getOne(lambdaQueryWrapper);
        }
        if (usr == null) {
            return R.error("登录失败");
        }
        if (!usr.getPassword().equals(password)) {
            System.out.println(password);
            return R.error("密码不正确");
        }
        // 查看用户状态 是否禁用 0为禁用 1正常
        if (usr.getStatus().equals("0")) {
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("user", usr.getId());
        System.out.println(R.success(usr));
        return R.success(usr);
    }
    @GetMapping("/captcha")
    public void captcha_calculation_arithmetic(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(133, 33);
        captcha.setLen(2);//几位数运算
        captcha.getArithmeticString();
        captcha.text();
        CaptchaConfig.CAPTCHA_MAP.put(key,captcha.text().toLowerCase());
        CaptchaUtil.out(captcha,request,response);
    }
    @GetMapping("/info")
    public R getUserInfo(@RequestParam("token") String token) {
        System.out.println("get-info-token:" + token);
        LambdaQueryWrapper<SysUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysUser::getId, token);
        SysUser user = sysUserService.getById(token);
        LambdaQueryWrapper<SysUserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleLambdaQueryWrapper.eq(SysUserRole::getUserId, token);
        List<SysUserRole> list = sysUserRoleService.list(userRoleLambdaQueryWrapper);
        JSONArray array = new JSONArray();
        if (list != null) {
            for (SysUserRole sysUserRole : list) {
                LambdaQueryWrapper<SysRole> sysRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
                sysRoleLambdaQueryWrapper.eq(SysRole::getId, sysUserRole.getRoleId());
                SysRole one = sysRoleService.getOne(sysRoleLambdaQueryWrapper);
                JSONObject obj = new JSONObject();
                obj.put("roleId", one.getId());
                obj.put("roleName", one.getRoleName());
                array.add(obj);
            }
        }
        JSONObject object = new JSONObject();
        object.put("user", user);
        object.put("role", array);
        return R.success(object);
    }

    @PostMapping("/logout")
    public R logout(@RequestHeader("X-Token") String token) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getId, token);
        SysUser user = sysUserService.getById(token);
        return R.success(user);
    }

    @GetMapping("/page")
    public R page(int page, int size, @RequestParam(defaultValue = "createTime,desc") String sort,
                  SysUserDto sysUserDto) {
        Sort.Direction direction = Sort.Direction.DESC;
        if (sort.contains(",asc")) {
            direction = Sort.Direction.ASC;
        }
        Sort sortObject = Sort.by(direction, sort.split(",")[0]);
        PageRequest pageRequest = PageRequest.of(page - 1, size, sortObject);
        return R.success(sysUserService.queryByPageWithRole(sysUserDto, pageRequest));
    }

    @GetMapping("/list")
    public R list(SysUserDto sysUserDto) {
        String sort = "createTime,desc";
        Sort.Direction direction = Sort.Direction.DESC;
        if (sort.contains(",asc")) {
            direction = Sort.Direction.ASC;
        }
        Sort sortObject = Sort.by(direction, sort.split(",")[0]);
        PageRequest pageRequest = PageRequest.of(0, 100, sortObject);
        return R.success(sysUserService.queryByPageWithRole(sysUserDto, pageRequest).getRecords());
    }

    @PostMapping("/add")
    public R addUser(HttpServletRequest request, @RequestBody SysUserDto user) {
        if (user.getPassword() == null || user.getPassword().equals("")) {
            user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        } else {
            user.setUsername(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        LambdaQueryWrapper<SysUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysUser::getPhone, user.getPhone());
        SysUser one = sysUserService.getOne(userLambdaQueryWrapper);
        if (one == null) {
            SysUser sysUser = new SysUser();
            sysUser.setAvatar(user.getAvatar());
            sysUser.setFullName(user.getFullName());
            sysUser.setUsername(user.getUsername());
            sysUser.setPhone(user.getPhone());
            sysUser.setPassword(user.getPassword());
            sysUser.setRemark(user.getRemark());
            sysUser.setSex(user.getSex());
            sysUser.setStatus(user.getStatus());
            sysUser.setId(TimeUtils.getTimestamp());
            if (sysUser.getUsername() == null) {
                sysUser.setUsername(user.getPhone());
            }
            if (sysUserService.save(sysUser)) {
                String id = sysUser.getId();
                List<Integer> roleIdSet = user.getRoleIdSet();
                for (Integer integer : roleIdSet) {
                    SysUserRole userRole = new SysUserRole();
                    userRole.setId(TimeUtils.getTimestamp());
                    userRole.setRoleId(integer);
                    userRole.setUserId(id);
                    sysUserRoleService.save(userRole);
                }
            } else {
                return R.error("操作错误");
            }
        } else {
            return R.error("该手机号已注册");
        }
        return R.success("操作成功");
    }

    /**
     * 用户自身重置密码
     *
     * @param user
     * @return
     */
    @PutMapping("pwd")
    public R pwd(@RequestBody SysUserDto user) {
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        LambdaQueryWrapper<SysUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysUser::getUsername, user.getUsername());
        userLambdaQueryWrapper.eq(SysUser::getPhone, user.getPassword());
        SysUser one = sysUserService.getOne(userLambdaQueryWrapper);
        if (one == null) {
            return R.error("重置失败，信息错误！");
        }
        one.setPassword(user.getPassword());
        if (sysUserService.updateById(one)) {
            return R.success("重置成功");

        } else {
            return R.error("操作失败");
        }
    }

    @PutMapping("admin-pwd")
    public R adminPwd(@RequestBody SysUserDto user) {
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        SysUser sysUser = new SysUser();
        sysUser.setId(user.getId());
        sysUser.setPassword(user.getPassword());
        if (sysUserService.updateById(sysUser)) {
            return R.success("重置成功");

        } else {
            return R.error("操作失败");
        }
    }

    @PutMapping("info")
    public R info(@RequestBody SysUserDto user) {
//        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        SysUser sysUser = new SysUser();
        sysUser.setId(user.getId());
        sysUser.setFullName(user.getFullName());
        sysUser.setUsername(user.getUsername());
        sysUser.setSex(user.getSex());
        sysUser.setPhone(user.getPhone());
        sysUser.setPassword(user.getPassword());
        if (sysUserService.updateById(sysUser)) {
            return R.success("重置成功");

        } else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysUserDto user) {
        LambdaQueryWrapper<SysUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysUser::getUsername, user.getUsername());
        List<SysUser> list = sysUserService.list(userLambdaQueryWrapper);
        if (list.size() > 1) {
            return R.error("该用户名已存在");
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(user.getId());
        sysUser.setAvatar(user.getAvatar());
        sysUser.setUsername(user.getPhone());
        sysUser.setFullName(user.getFullName());
        sysUser.setUsername(user.getUsername());
        sysUser.setPassword(user.getPassword());
        sysUser.setRemark(user.getRemark());
        sysUser.setSex(user.getSex());
        sysUser.setStatus(user.getStatus());
        if (sysUser.getUsername() == null) {
            sysUser.setUsername(user.getPhone());
        }
        if (sysUserService.updateById(sysUser)) {
            String id = sysUser.getId();
            List<Integer> roleIdSet = user.getRoleIdSet();
                LambdaQueryWrapper<SysUserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
                userRoleLambdaQueryWrapper.eq(SysUserRole::getUserId, id);
                sysUserRoleService.remove(userRoleLambdaQueryWrapper);
                for (Integer integer : roleIdSet) {
                    SysUserRole userRole = new SysUserRole();
                    userRole.setId(TimeUtils.getTimestamp());
                    userRole.setRoleId(integer);
                    userRole.setUserId(id);
                    sysUserRoleService.saveOrUpdate(userRole);
                }
        } else {
            return R.error("操作错误");
        }
        return R.success("操作成功");
    }

    @DeleteMapping
    public R delete(@RequestBody SysUserDto userDto) {
        String id = userDto.getId();
        LambdaQueryWrapper<SysUserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleLambdaQueryWrapper.eq(SysUserRole::getUserId, id);
        sysUserRoleService.remove(userRoleLambdaQueryWrapper);
        SysUser sysUser = new SysUser();
        sysUser.setId(userDto.getId());
        sysUserService.removeById(id);
        return R.success("删除成功！");
    }

    @GetMapping
    public R get(){
        return R.success(sysUserService.list());
    }
}

