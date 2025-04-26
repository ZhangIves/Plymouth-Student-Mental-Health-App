package com.loong.x.uniapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysBanner;
import com.loong.x.app.entity.SysComplaint;
import com.loong.x.app.service.SysBannerService;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
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
@RequestMapping("/clientApi/home")
public class ClientBannerController extends BaseController {
    @Resource
    private SysBannerService bannerService;
    @GetMapping("/banner")
    public ResponseObject page(HttpServletRequest request) {
        String token = request.getHeader("Access-Token");
        if (token.equals("")){
            return getFailureResult(0,"请先登录！");
        }
        Page pageInfo = new Page(1, 5);
        LambdaQueryWrapper<SysBanner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysBanner::getStatus, 1);
        queryWrapper.orderByDesc(SysBanner::getSortNum);
        queryWrapper.orderByAsc(SysBanner::getCreateTime);
        Page page1 = bannerService.page(pageInfo, queryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", page1.getRecords());
        outParams.put("pageSize", page1.getSize());
        outParams.put("pageNumber", page1.getCurrent());
        outParams.put("totalRow", page1.getPages());
        outParams.put("totalPage", page1.getTotal());
        return getSuccessResult(outParams);
    }
}
