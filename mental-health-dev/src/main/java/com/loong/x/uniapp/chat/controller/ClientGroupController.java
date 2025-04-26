package com.loong.x.uniapp.chat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loong.x.app.service.SysUserService;
import com.loong.x.chat.model.entity.Group;
import com.loong.x.chat.model.vo.GroupUserJoinVo;
import com.loong.x.chat.model.vo.GroupUserVo;
import com.loong.x.chat.model.vo.GroupVo;
import com.loong.x.chat.model.vo.MsgHandleVo;
import com.loong.x.chat.service.GroupService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description
 * 群组
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/16 10:16
 */
@RestController
@RequestMapping("/clientApi/group")
public class ClientGroupController {

    @Resource
    private GroupService groupService;
    @Resource
    private SysUserService sysUserService;

    /**
     * 群组--新增
     */
    @PostMapping
    public R add(final @RequestBody GroupVo groupVo, HttpServletRequest request) {
        String header = request.getHeader("Access-Token");
        long parseLong = Long.parseLong(header);
        GroupVo resGroupVo = groupService.add(groupVo, parseLong);
        return R.success(resGroupVo);
    }

    @GetMapping("/page")
    public R queryByPage(Group group, LXPageRequest pageRequest, HttpServletRequest request) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<Group> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(group.getName())) {
            queryWrapper.and(c -> c.like(Group::getName, group.getName()));
        }
        queryWrapper.orderByDesc(Group::getCreateTime);
        groupService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }


    /**
     * 群组列表
     *
     * @return
     */
    @GetMapping
    public R get(final GroupUserVo groupUserVo,
                 final @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                 final @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                 HttpServletRequest request) {
        String token = request.getHeader("Access-Token");
        if(token.equals("")){
            return R.error("请先登录");
        }
        long parseLong = Long.parseLong(token);
        IPage<GroupVo> page = groupService.page(groupUserVo, current, size, parseLong);
        return R.success(page);
    }

    /**
     * 群组详情
     *
     * @param groupId
     * @return
     */
    @GetMapping("/{groupId}")
    public R detail(@PathVariable String groupId) {
        GroupVo groupVo = groupService.detail(groupId);
        return R.success(groupVo);
    }

    /**
     * 拉人入群
     *
     * @param groupUserJoinVo
     * @return
     */
    @PostMapping("/pull")
    public R pull(@RequestBody GroupUserJoinVo groupUserJoinVo, HttpServletRequest request) {
        String header = request.getHeader("Access-Token");
        long parseLong = Long.parseLong(header);
        groupService.pull(groupUserJoinVo, parseLong);
        return R.success(null);
    }

    /**
     * 拉人入群
     *
     * @param groupUserJoinVo
     * @return
     */
    @PostMapping("/join")
    public R join(@RequestBody GroupUserJoinVo groupUserJoinVo) {
        groupService.join(groupUserJoinVo);
        return R.success(null);
    }

    /**
     * 群组消息处理
     *
     * @param msgHandleVo
     * @return
     */
    @PutMapping("/msgHandle")
    public R msgHandle(@RequestBody MsgHandleVo msgHandleVo) {
        groupService.msgHandle(msgHandleVo);
        return R.success(null);
    }
}
