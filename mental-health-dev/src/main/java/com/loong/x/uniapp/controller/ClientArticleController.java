package com.loong.x.uniapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysBanner;
import com.loong.x.app.entity.SysNews;
import com.loong.x.app.service.SysBannerService;
import com.loong.x.app.service.SysNewsService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/clientApi/article")
public class ClientArticleController extends BaseController {
    @Resource
    private SysNewsService sysNewsService;
    @GetMapping("/page")
    public ResponseObject page(HttpServletRequest request, SysNews news, LXPageRequest pageRequest) {
        String token = request.getHeader("Access-Token");
        if (token.equals("")){
            return getFailureResult(0,"请先登录！");
        }
        Page pageInfo = new Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysNews> queryWrapper = new LambdaQueryWrapper<>();
        if (news.getStatus() != null) {
            queryWrapper.eq(SysNews::getStatus, news.getStatus());
        }
        if (StringUtils.hasLength(news.getTitle())) {
            queryWrapper.and(c -> c.like(SysNews::getTitle, news.getTitle()));
        }
        queryWrapper.orderByDesc(SysNews::getCreateTime);
        Page page1 = sysNewsService.page(pageInfo, queryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", page1.getRecords());
        outParams.put("pageSize", page1.getSize());
        outParams.put("pageNumber", page1.getCurrent());
        outParams.put("totalRow", page1.getPages());
        outParams.put("totalPage", page1.getTotal());
        return getSuccessResult(outParams);
    }

    @GetMapping("/detail")
    public ResponseObject detail(SysNews news) {
        return getSuccessResult(sysNewsService.getById(news.getId()));
    }
}
