package com.loong.x.uniapp.chat.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loong.x.chat.model.vo.FriendMsgVo;
import com.loong.x.chat.model.vo.MsgHandleVo;
import com.loong.x.chat.service.FriendMsgService;
import com.loong.x.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description
 * 好友消息
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/16 10:16
 */
@RestController
@Slf4j
@RequestMapping("/clientApi/friendMsg")
public class ClientFriendMsgController {

    @Resource
    private FriendMsgService friendMsgService;

    @PostMapping
    public R send(@RequestBody FriendMsgVo friendMsgVo) {
        log.info("消息数据,friendMsgVo = {}", JSONUtil.toJsonStr(friendMsgVo));
        FriendMsgVo resFriendMsgVo = friendMsgService.add(friendMsgVo);
        return R.success(resFriendMsgVo);
    }

    /**
     * 好友消息--获取列表数据
     * @param friendMsgVo
     * @param current
     * @param size
     * @return
     */
    @GetMapping
    public R get(final FriendMsgVo friendMsgVo,
                            final @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                            final @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        IPage<FriendMsgVo> page = friendMsgService.page(friendMsgVo, current, size);
        return R.success(page);
    }

    /**
     * 好友消息撤回
     * @param msgHandleVo
     * @return
     */
    @PutMapping("/msgHandle")
    public R revoke(@RequestBody MsgHandleVo msgHandleVo) {
        friendMsgService.msgHandle(msgHandleVo);
        return R.success("成功");
    }
}
