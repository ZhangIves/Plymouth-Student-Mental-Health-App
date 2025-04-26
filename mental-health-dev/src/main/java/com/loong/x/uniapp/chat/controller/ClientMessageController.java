package com.loong.x.uniapp.chat.controller;

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
 * Description
 * 好友消息/群组消息控制器
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/16 10:16
 */
@RestController
@Slf4j
@RequestMapping("/clientApi/message")
public class ClientMessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public R list(MessageVo messageVo, HttpServletRequest request) {
        String token = request.getHeader("Access-Token");
        if(token.equals("")){
            return R.error("请先登录");
        }
        long parseLong = Long.parseLong(token);
        log.info("参数数据,messageVo = {}", JSONUtil.toJsonStr(messageVo));
        List<MessageVo> listMessageVo = messageService.list(messageVo, parseLong);
        return R.success(listMessageVo);
    }

}
