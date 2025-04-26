package com.loong.x.uniapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysComplaint;
import com.loong.x.app.entity.SysZone;
import com.loong.x.app.entity.SysZoneLog;
import com.loong.x.app.service.SysComplaintService;
import com.loong.x.app.service.SysZoneLogService;
import com.loong.x.app.service.SysZoneService;
import com.loong.x.common.utils.TimeUtils;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/4/27 11:23
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/clientApi/complaint")
public class ClientComplaintController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private SysComplaintService sysComplaintService;

    @GetMapping("/page")
    public ResponseObject page(HttpServletRequest request,int page) {
        String token = request.getHeader("Access-Token");
        Page pageInfo = new Page(page, 10);
        LambdaQueryWrapper<SysComplaint> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysComplaint::getUserId, token);
        queryWrapper.orderByAsc(SysComplaint::getCreateTime);
        Page page1 = sysComplaintService.page(pageInfo, queryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", page1.getRecords());
        outParams.put("pageSize", page1.getSize());
        outParams.put("pageNumber", page1.getCurrent());
        outParams.put("totalRow", page1.getPages());
        outParams.put("totalPage", page1.getTotal());
        return getSuccessResult(outParams);
    }

    @PostMapping("/save")
    public ResponseObject add(HttpServletRequest request, @RequestBody SysComplaint complaint) {
        String token = request.getHeader("Access-Token");
        complaint.setUserId(token);
        if(sysComplaintService.save(complaint)){
            return getSuccessResult(complaint);
        }
        return getFailureResult(0,"操作失败");
    }

    // 查看我的预约
    @GetMapping("/list")
    public ResponseObject list(HttpServletRequest request,int page) {
        Page pageInfo = new Page(page, 20);
        String token = request.getHeader("Access-Token");
        LambdaQueryWrapper<SysComplaint> logLambdaQueryWrapper = new LambdaQueryWrapper<>();
        logLambdaQueryWrapper.eq(SysComplaint::getUserId,token);
        logLambdaQueryWrapper.orderByDesc(SysComplaint::getCreateTime);
        Page page1 = sysComplaintService.page(pageInfo, logLambdaQueryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", page1.getRecords());
        outParams.put("pageSize", page1.getSize());
        outParams.put("pageNumber", page1.getCurrent());
        outParams.put("totalRow", page1.getPages());
        outParams.put("totalPage", page1.getTotal());

        return getSuccessResult(outParams);
    }
}
