package com.loong.x.uniapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysNews;
import com.loong.x.app.entity.SysQuestion;
import com.loong.x.app.service.SysNewsService;
import com.loong.x.app.service.SysQuestionService;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/13 23:49
 */
@RestController
@RequestMapping("/clientApi/question")
public class ClientQuestionController extends BaseController {
    @Resource
    private SysQuestionService sysQuestionService;
    @GetMapping("/page")
    public ResponseObject page(HttpServletRequest request,SysNews news) {
        String token = request.getHeader("Access-Token");
        Page pageInfo = new Page(1, 5);
        LambdaQueryWrapper<SysQuestion> queryWrapper = new LambdaQueryWrapper<>();
        if (news.getStatus() != null) {
            queryWrapper.eq(SysQuestion::getStatus, news.getStatus());
        }
        if (StringUtils.hasLength(news.getTitle())) {
            queryWrapper.and(c -> c.like(SysQuestion::getTitle, news.getTitle()));
        }
        queryWrapper.orderByDesc(SysQuestion::getCreateTime);
        Page page1 = sysQuestionService.page(pageInfo, queryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", page1.getRecords());
        outParams.put("pageSize", page1.getSize());
        outParams.put("pageNumber", page1.getCurrent());
        outParams.put("totalRow", page1.getPages());
        outParams.put("totalPage", page1.getTotal());
        return getSuccessResult(outParams);
    }

    @GetMapping("/detail")
    public ResponseObject detail(SysQuestion question) {
        return getSuccessResult(sysQuestionService.getById(question.getId()));
    }

    @PostMapping
    public ResponseObject add(HttpServletRequest request, SysQuestion sysQuestion){
        String token = request.getHeader("Access-Token");
        sysQuestion.setUserId(token);
        sysQuestionService.save(sysQuestion);
        return getSuccessResult(sysQuestion);
    }
}
