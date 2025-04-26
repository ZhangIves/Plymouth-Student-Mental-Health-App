package com.loong.x.uniapp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysRole;
import com.loong.x.app.entity.SysUser;
import com.loong.x.app.entity.SysUserRole;
import com.loong.x.app.service.SysRoleService;
import com.loong.x.app.service.SysRolesMenusService;
import com.loong.x.app.service.SysUserRoleService;
import com.loong.x.app.service.SysUserService;
import com.loong.x.app.vo.UserVo;
import com.loong.x.chat.model.entity.Friend;
import com.loong.x.chat.model.vo.FriendVo;
import com.loong.x.chat.service.FriendService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import com.loong.x.common.utils.TimeUtils;
import com.loong.x.uniapp.exception.BusinessCheckException;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/3/19 15:11
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/clientApi/user")
public class ClientUserController extends BaseController {
    /**
     * 系统环境变量
     */
    private Environment env;
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysRolesMenusService menusService;

    @Resource
    private FriendService friendService;

    /**
     * 好友保存
     *
     * @param friendVo
     * @return
     */
    @PostMapping("/helfrin")
    public R add(final @RequestBody FriendVo friendVo, HttpServletRequest request) {
        String header = request.getHeader("Access-Token");
        long parseLong = Long.parseLong(header);
        FriendVo resFriendVo = friendService.add(friendVo, parseLong);
        return R.success(resFriendVo);
    }

    @PostMapping("/login")
    public ResponseObject login(HttpServletRequest request, @RequestBody UserVo user) {
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<SysUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysUser::getPhone, user.getPhone());
        SysUser usr = sysUserService.getOne(userLambdaQueryWrapper);
        if (usr == null) {
            LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SysUser::getPhone, user.getUsername());
            usr = sysUserService.getOne(lambdaQueryWrapper);
        }
        if (usr == null) {
            return getFailureResult(201, "账号或密码有误");
        }
        LambdaQueryWrapper<SysUserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserRole::getUserId, usr.getId());
        SysUserRole role = sysUserRoleService.getOne(lambdaQueryWrapper);
        if (role.getRoleId() != 2) {
            return getFailureResult(201, "账号或密码有误");
        }
        if (!usr.getPassword().equals(password)) {
            return getFailureResult(201, "账号或密码有误");
        }
        // 查看用户状态 是否禁用 1为禁用 0正常
        if (usr.getStatus() == 1) {
            return getFailureResult(201, "账号已禁用");
        }

        Map<String, Object> outParams = new HashMap<>();
        outParams.put("token", usr.getId());
        outParams.put("id", usr.getId());
        outParams.put("userId", usr.getId());
        outParams.put("userName", usr.getPhone());
        String domain = env.getProperty("website.url");
        String appId = env.getProperty("weixin.official.appId");
        outParams.put("domain", domain);
        outParams.put("appId", appId);
        return getSuccessResult("登录成功", outParams);
    }

    @GetMapping("/info")
    public ResponseObject info(HttpServletRequest request) throws BusinessCheckException {
        String token = request.getHeader("Access-Token");
        SysUser user = sysUserService.getById(token);
        if (user == null) {
            return getFailureResult(0, "用户没登录!");
        }
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
        object.put("fullName", user.getFullName());
        object.put("avatar", user.getAvatar());
        object.put("phone", user.getPhone());
        object.put("sex", user.getSex());
        object.put("id", user.getId());
        object.put("user", user);
        object.put("role", array);
        return getSuccessResult(object);
    }

    //    @PostMapping("/logout")
//    public R logout(@RequestHeader("X-Token") String token) {
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        // 等值查询
//        userLambdaQueryWrapper.eq(User::getId, token);
//        SysUser user = sysUserService.getById(token);
//        return R.success(user);
//    }
    @GetMapping("/{userId}")
    public ResponseObject detail(HttpServletRequest request,@PathVariable Long userId) {
        String token = request.getHeader("Access-Token");
        if(token.equals("")){
            return getFailureResult(0,"请先登录");
        }
        return getSuccessResult(sysUserService.getById(userId));
    }

    @PostMapping("/register")
    public ResponseObject register(HttpServletRequest request, @RequestBody SysUser user) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getPhone, user.getPhone());
        SysUser one = sysUserService.getOne(queryWrapper);
        if (one == null) {
            String password = user.getPassword();
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setId(TimeUtils.getTimestamp());
            user.setUsername(user.getPhone());
            user.setPassword(password);
            sysUserService.save(user);
            LambdaQueryWrapper<SysUser> friendLambdaQueryWrapper = new LambdaQueryWrapper<>();
            friendLambdaQueryWrapper.eq(SysUser::getRemark,"doctor");
            List<SysUser> list = sysUserService.list(friendLambdaQueryWrapper);
            for (SysUser sysUser : list) {
                FriendVo vo = new FriendVo();
                if (sysUser.getFullName()!=null&& !sysUser.getFullName().equals("")){
                    vo.setAlphabetic(sysUser.getFullName().substring(0,1).toUpperCase());
                }
                vo.setAvatar(sysUser.getAvatar());
                vo.setFriendId(Long.parseLong(sysUser.getId()));
                vo.setNickname(sysUser.getFullName()+" Doctor");
                vo.setUserId(Long.parseLong(user.getId()));
                FriendVo resFriendVo = friendService.add(vo, Long.parseLong(user.getId()));

                FriendVo vo1 = new FriendVo();
                if (user.getFullName()!=null&& !user.getFullName().equals("")){
                    vo1.setAlphabetic(user.getFullName().substring(0,1).toUpperCase());
                }
                vo1.setAvatar(user.getAvatar());
                vo1.setFriendId(Long.parseLong(user.getId()));
                vo1.setNickname(user.getFullName());
                vo1.setUserId(Long.parseLong(sysUser.getId()));

                friendService.add(vo1, Long.parseLong(sysUser.getId()));

            }
            SysUserRole userRole = new SysUserRole();
            userRole.setId(TimeUtils.getTimestamp());
            userRole.setRoleId(2);
            userRole.setUserId(user.getId());
            sysUserRoleService.save(userRole);
            return getSuccessResult(user);
        } else {
            return getFailureResult(201, "该手机号已注册");
        }

    }

    /**
     * 保存会员信息
     */
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseObject saveInfo(HttpServletRequest request, @RequestBody Map<String, Object> param) throws BusinessCheckException {
        String token = request.getHeader("Access-Token");
        String name = param.get("fullName") == null ? "" : param.get("fullName").toString();
        String avatar = param.get("avatar") == null ? "" : param.get("avatar").toString();
        Integer sex = param.get("sex") == null ? 1 : Integer.parseInt(param.get("sex").toString());
        String password = param.get("password") == null ? "" : param.get("password").toString();
        String passwordOld = param.get("passwordOld") == null ? "" : param.get("passwordOld").toString();
        String phone = param.get("phone") == null ? "" : param.get("phone").toString();
        SysUser userInfo = sysUserService.getById(token);
        boolean modifyPassword = false;
        if (userInfo == null) {
            return getFailureResult(1001);
        }
        userInfo.setSex(sex);

        if (StringUtils.isNotEmpty(name)) {
            userInfo.setFullName(name);
        }
        if (StringUtils.isNotEmpty(password)) {
            if (StringUtils.isNotEmpty(passwordOld)) {
                String pass = DigestUtils.md5DigestAsHex(passwordOld.getBytes());
                if (!pass.equals(userInfo.getPassword())) {
                    return getFailureResult(201, "旧密码输入有误");
                }else{
                    userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
                }
            }
            modifyPassword = true;
        }
        if (StringUtils.isNotEmpty(phone)) {
            userInfo.setPhone(phone);
        }
        if (StringUtils.isNotEmpty(avatar)) {
            userInfo.setAvatar(avatar);
        }

        sysUserService.updateById(userInfo);
        return getSuccessResult(userInfo);
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseObject doLogout(HttpServletRequest request) {
        String token = request.getHeader("Access-Token");
//        boolean flag = TokenUtil.removeToken(token);
//        if (!flag) {
//            return getFailureResult(1001, "退出错误!");
//        } else {
        return getSuccessResult("退出成功！");
//        }
    }

    @PostMapping("/resetPwd")
    public ResponseObject resetPwd(@RequestBody SysUser sysUser) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getPhone, sysUser.getPhone());
        SysUser one = sysUserService.getOne(queryWrapper);
        if (one == null) {
            return getFailureResult(201, "信息匹配错误，重置密码失败！");
        } else {
//            one.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            one.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
            if (sysUserService.updateById(one)) {
                return getSuccessResult("重置密码成功");
            }
        }
        return getFailureResult(201, "操作失败");
    }

    @GetMapping
    public ResponseObject getByPhone(LXPageRequest pageRequest,SysUser sysUser){
        Page pageInfo = new Page(1, 5);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(sysUser.getUsername()),SysUser::getPhone,sysUser.getUsername());
        sysUserService.page(pageInfo,queryWrapper);
        return getSuccessResult(pageInfo);

    }

}
