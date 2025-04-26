package com.loong.x.chat.controller;

import cn.hutool.json.JSONUtil;
import com.loong.x.chat.model.vo.MessageVo;
import com.loong.x.chat.service.MessageService;
import com.loong.x.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 好友消息/群组消息控制器
 * @Author: hdx
 * @Date: 2022/2/16 17:37
 * @Version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public R list(MessageVo messageVo, HttpServletRequest request) {
        String header = request.getHeader("x-Token");
        long parseLong = Long.parseLong(header);
        log.info("参数数据,messageVo = {}", JSONUtil.toJsonStr(messageVo));
        List<MessageVo> listMessageVo = messageService.list(messageVo,parseLong);
        return R.success(listMessageVo);
    }

}
