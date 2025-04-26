package com.loong.x.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysZone;
import com.loong.x.app.entity.SysZoneLog;
import com.loong.x.app.mapper.SysZoneLogMapper;
import com.loong.x.app.service.SysZoneLogService;
import com.loong.x.app.service.SysZoneService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import com.loong.x.uniapp.utils.ResponseObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysZoneLog)表控制层
 *
 * @author makejava
 * @since 2024-04-25 13:20:11
 */
@RestController
@RequestMapping("sysZoneLog")
public class SysZoneLogController {
    /**
     * 服务对象
     */
    @Resource
    private SysZoneLogService sysZoneLogService;
    @GetMapping("/page")
        public R<Page> page(LXPageRequest pageRequest, SysZoneLog zoneLog) {
        Page pageInfo = new Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysZoneLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(zoneLog.getZoneId()),SysZoneLog::getZoneId,zoneLog.getZoneId());
        queryWrapper.orderByDesc(SysZoneLog::getCreateTime);
        sysZoneLogService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R add(@RequestBody SysZoneLog zoneLog) {

        if (this.sysZoneLogService.save(zoneLog)){
            return R.success("添加成功！");
        }else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysZoneLog zoneLog) {
        sysZoneLogService.updateById(zoneLog);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R delete(@RequestBody SysZoneLog zoneLog) {
        sysZoneLogService.removeById(zoneLog);
        return R.success("删除成功！");
    }

    @Resource
    private SysZoneService sysZoneService;

    @Resource
    private SysZoneLogMapper logMapper;
    @GetMapping("/detail")
    public R detail(HttpServletRequest request, String zoneId) {
        List<JSONObject> commentList = logMapper.getCommentList(zoneId);
        SysZone zone = sysZoneService.getById(zoneId);
        Map<String, Object> outParams = new HashMap();
        outParams.put("comments", commentList);
        outParams.put("zone", zone);
        return R.success(outParams);
    }
}

