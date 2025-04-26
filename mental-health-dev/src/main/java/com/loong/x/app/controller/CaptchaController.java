package com.loong.x.app.controller;

import com.loong.x.common.config.CaptchaConfig;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/4/22 0:50
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @GetMapping
    public void captcha_calculation_arithmetic(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(133, 33);
        captcha.setLen(2);//几位数运算，默认是2位
        captcha.getArithmeticString();
        captcha.text();
        CaptchaConfig.CAPTCHA_MAP.put(key,captcha.text().toLowerCase());
        CaptchaUtil.out(captcha,request,response);
    }
}
