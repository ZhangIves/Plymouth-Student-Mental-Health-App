package com.loong.x.uniapp.chat.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loong.x.chat.model.vo.GroupMsgVo;
import com.loong.x.chat.service.GroupMsgService;
import com.loong.x.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description
 * 群组消息
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/16 10:16
 */
@RestController
@Slf4j
@RequestMapping("/clientApi/groupMsg")
public class ClientGroupMsgController {

    @Resource
    private GroupMsgService groupMsgService;

    @PostMapping
    public R send(@RequestBody GroupMsgVo groupMsgVo) {
        log.info("消息数据,groupMsgVo = {}", JSONUtil.toJsonStr(groupMsgVo));
        GroupMsgVo resGroupMsgVo = groupMsgService.add(groupMsgVo);
        return R.success(resGroupMsgVo);
    }

    /**
     * 群组消息--获取列表数据
     * @param groupMsgVo
     * @param current
     * @param size
     * @return
     */
    @GetMapping
    public R get(final GroupMsgVo groupMsgVo,
                 final @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                 final @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                 HttpServletRequest request) {
        String token = request.getHeader("Access-Token");
        long parseLong = Long.parseLong(token);
        IPage<GroupMsgVo> page = groupMsgService.page(groupMsgVo, current, size,parseLong);
        return R.success(page);
    }

}
