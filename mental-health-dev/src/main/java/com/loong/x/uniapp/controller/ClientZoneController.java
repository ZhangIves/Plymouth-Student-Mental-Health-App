package com.loong.x.uniapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysZone;
import com.loong.x.app.entity.SysZoneLog;
import com.loong.x.app.entity.SysZoneType;
import com.loong.x.app.mapper.SysZoneLogMapper;
import com.loong.x.app.service.SysUserService;
import com.loong.x.app.service.SysZoneLogService;
import com.loong.x.app.service.SysZoneService;
import com.loong.x.app.service.SysZoneTypeService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import com.loong.x.common.utils.TimeUtils;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
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
    @RequestMapping(value = "/clientApi/zone")
public class ClientZoneController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private SysZoneService sysZoneService;
    @Resource
    private SysZoneLogService logService;
    @Resource
    private SysZoneTypeService typeService;
    @Resource
    private SysUserService sysUserService;
    // 上方分类
    @GetMapping("type")
    public ResponseObject typeList(){
        LambdaQueryWrapper<SysZoneType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysZoneType::getStatus,1);
        queryWrapper.orderByDesc(SysZoneType::getSort);
        List<SysZoneType> list = typeService.list(queryWrapper);
        return getSuccessResult(list);
    }
    // 帖子列表
    @GetMapping("/page")
    public ResponseObject page(HttpServletRequest request,LXPageRequest pageRequest, SysZone zone) {
        String token = request.getHeader("Access-Token");
        if (token.equals("")){
            return getFailureResult(0,"请先登录！");
        }
        Page pageInfo = new Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysZone> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysZone::getStatus, 1);
        if (zone.getZoneTypeId() != null && zone.getZoneTypeId() != 0){
            queryWrapper.eq(SysZone::getZoneTypeId,zone.getZoneTypeId());
        }
        queryWrapper.orderByDesc(SysZone::getCreateTime);
        Page page1 = sysZoneService.page(pageInfo, queryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", page1.getRecords());
        outParams.put("pageSize", page1.getSize());
        outParams.put("pageNumber", page1.getCurrent());
        outParams.put("totalRow", page1.getPages());
        outParams.put("totalPage", page1.getTotal());
        return getSuccessResult(outParams);
    }

    // 发帖
    @PostMapping("/save")
    public ResponseObject add(HttpServletRequest request, @RequestBody SysZone zone) {
        String token = request.getHeader("Access-Token");
        zone.setId(TimeUtils.getTimestamp());
        zone.setUserId(token);
        if(sysZoneService.save(zone)){
            return getSuccessResult(zone);
        }
        return getFailureResult(0,"操作失败");
    }

    // 删帖
    @DeleteMapping
    public ResponseObject delete(@RequestBody SysZone zone){
        return getSuccessResult(sysZoneService.removeById(zone));
    }

    // 改
    @PostMapping("/update")
    public ResponseObject update(HttpServletRequest request, @RequestBody SysZone zone) {
        String token = request.getHeader("Access-Token");
        if(sysZoneService.updateById(zone)){
            return getSuccessResult(zone);
        }
        return getFailureResult(0,"操作失败");
    }

    // 查看评论
    @GetMapping("/list")
    public ResponseObject list(HttpServletRequest request,int page) {
        Page pageInfo = new Page(page, 20);
        String token = request.getHeader("Access-Token");
        LambdaQueryWrapper<SysZoneLog> logLambdaQueryWrapper = new LambdaQueryWrapper<>();
        logLambdaQueryWrapper.eq(SysZoneLog::getUserId,token);
        logLambdaQueryWrapper.orderByDesc(SysZoneLog::getCreateTime);
        Page page1 = logService.page(pageInfo, logLambdaQueryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", page1.getRecords());
        outParams.put("pageSize", page1.getSize());
        outParams.put("pageNumber", page1.getCurrent());
        outParams.put("totalRow", page1.getPages());
        outParams.put("totalPage", page1.getTotal());
        return getSuccessResult(outParams);
    }

    @Resource
    private SysZoneLogMapper logMapper;
    // 查看详情
    @GetMapping("/detail")
    public ResponseObject detail(HttpServletRequest request,String zoneId ,String logId) {
        String token = request.getHeader("Access-Token");
        LambdaQueryWrapper<SysZoneLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(zoneId),SysZoneLog::getZoneId,zoneId);
        queryWrapper.orderByDesc(SysZoneLog::getCreateTime);
        List<SysZoneLog> list = logService.list(queryWrapper);
        List<JSONObject> commentList = logMapper.getCommentList(zoneId);
        SysZone zone = sysZoneService.getById(zoneId);
        Map<String, Object> outParams = new HashMap();
        outParams.put("comments", commentList);
        outParams.put("zone", zone);
        return getSuccessResult(outParams);
    }

    // 新增评论
    @PostMapping("/comment")
    public ResponseObject addComment(HttpServletRequest request,@RequestBody SysZoneLog zoneLog) {
        String token = request.getHeader("Access-Token");
        zoneLog.setId(TimeUtils.getTimestamp());
        zoneLog.setUserId(token);
        logService.save(zoneLog);
        return getSuccessResult(zoneLog);
    }

    @DeleteMapping("/comment")
    public ResponseObject deleteComment(HttpServletRequest request,@RequestBody SysZoneLog zoneLog) {
        return getSuccessResult(logService.removeById(zoneLog));
    }

}
