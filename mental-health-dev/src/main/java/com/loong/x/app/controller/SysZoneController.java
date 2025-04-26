package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysZone;
import com.loong.x.app.service.SysZoneService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import com.loong.x.common.utils.TimeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (SysZone)表控制层
 * 发帖
 * @author makejava
 * @since 2024-04-24 23:52:32
 */
@RestController
@RequestMapping("sysZone")
public class SysZoneController {
    /**
     * 服务对象
     */
    @Resource
    private SysZoneService sysZoneService;
    @GetMapping
    public R<Page> page(LXPageRequest pageRequest, SysZone zone) {
        Page pageInfo = new Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysZone> queryWrapper = new LambdaQueryWrapper<>();
        if (zone.getStatus() != null){
            queryWrapper.eq(SysZone::getStatus,zone.getStatus());
        }
        if (zone.getZoneTypeId() != null){
            queryWrapper.eq(SysZone::getZoneTypeId,zone.getZoneTypeId());
        }
        if (StringUtils.hasLength(zone.getZoneTitle())) {
            queryWrapper.and(c -> c.like(SysZone::getZoneTitle, zone.getZoneTitle()));
        }
        queryWrapper.orderByAsc(SysZone::getCreateTime);
        sysZoneService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R add(@RequestBody SysZone zone, HttpServletRequest request) {
        String userId = request.getHeader("X-Token");
        zone.setId(TimeUtils.getTimestamp());
        zone.setUserId(userId);
        if (this.sysZoneService.save(zone)){
            return R.success("添加成功！");
        }else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysZone zone) {
        sysZoneService.updateById(zone);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R delete(@RequestBody SysZone zone) {
        sysZoneService.removeById(zone);
        return R.success("删除成功！");
    }

}

