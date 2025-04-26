package com.loong.x.uniapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.Notice;
import com.loong.x.app.entity.SysRepairLog;
import com.loong.x.app.service.NoticeService;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/4/28 9:38
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/clientApi/notice")
public class ClientNoticeController extends BaseController {
    @Resource
    private NoticeService noticeService;
    @GetMapping("/list")
    public ResponseObject list(HttpServletRequest request, int page) {
        Page pageInfo = new Page(page, 20);
        String token = request.getHeader("Access-Token");
        LambdaQueryWrapper<Notice> logLambdaQueryWrapper = new LambdaQueryWrapper<>();
        logLambdaQueryWrapper.eq(Notice::getStatus,1);
        logLambdaQueryWrapper.orderByDesc(Notice::getCreateTime);
        Page page1 = noticeService.page(pageInfo, logLambdaQueryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", page1.getRecords());
        outParams.put("pageSize", page1.getSize());
        outParams.put("pageNumber", page1.getCurrent());
        outParams.put("totalRow", page1.getPages());
        outParams.put("totalPage", page1.getTotal());

        return getSuccessResult(outParams);
    }
    @GetMapping("/page")
    public ResponseObject page(HttpServletRequest request, int page,int pageSize) {
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Notice> logLambdaQueryWrapper = new LambdaQueryWrapper<>();
        logLambdaQueryWrapper.eq(Notice::getStatus,1);
        logLambdaQueryWrapper.orderByDesc(Notice::getUpdateTime);
        Page page1 = noticeService.page(pageInfo, logLambdaQueryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", page1.getRecords());
        outParams.put("pageSize", page1.getSize());
        outParams.put("pageNumber", page1.getCurrent());
        outParams.put("totalRow", page1.getPages());
        outParams.put("totalPage", page1.getTotal());

        return getSuccessResult(outParams);
    }

    @PostMapping("detail")
    public ResponseObject detail(HttpServletRequest request, @RequestBody Map<String, Object> param) {
        String token = request.getHeader("Access-Token");
        String articleId = param.get("articleId").toString();
        long parseLong = Long.parseLong(articleId);
        return getSuccessResult(noticeService.queryById(parseLong));
    }

}
