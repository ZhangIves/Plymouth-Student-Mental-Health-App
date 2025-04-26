package com.loong.x.uniapp.chat.controller;

import com.loong.x.app.service.SysUserService;
import com.loong.x.chat.enums.ServiceErrorEnum;
import com.loong.x.chat.exception.ServiceException;
import com.loong.x.chat.model.vo.RegisterVo;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description
 * 注册
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/16 10:16
 */
@RestController
@RequestMapping("/clientApi/register")
public class ClientRegisterController extends BaseController {

    @Resource
    private SysUserService userService;

    /**
     * 用户自主注册
     * @param registerVo
     * @return
     */
    @PostMapping
    public ResponseObject register(@RequestBody RegisterVo registerVo) {
        // 校验两次密码是否一致
        if (!registerVo.getPassword().equals(registerVo.getPasswordVerify())) {
            throw new ServiceException(ServiceErrorEnum.CONFIRMATION_PASSWORD_ERROR);
        }
//        userService.save(registerVo);
        return getSuccessResult(null);
    }
}
