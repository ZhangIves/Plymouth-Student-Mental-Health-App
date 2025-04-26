package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loong.x.app.entity.SysZoneType;
import com.loong.x.app.service.SysZoneTypeService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysZoneType)表控制层
 *
 * @author makejava
 * @since 2024-05-10 23:35:53
 */
@RestController
@RequestMapping("sysZoneType")
public class SysZoneTypeController {
    /**
     * 服务对象
     */
    @Resource
    private SysZoneTypeService sysZoneTypeService;

    /**
     * 分页查询
     *
     * @param sysZoneType 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public R queryByPage(SysZoneType sysZoneType, LXPageRequest pageRequest) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysZoneType> queryWrapper = new LambdaQueryWrapper<>();
        if (sysZoneType.getStatus() != null) {
            queryWrapper.eq(SysZoneType::getStatus, sysZoneType.getStatus());
        }
        if (StringUtils.hasLength(sysZoneType.getName())) {
            queryWrapper.and(c -> c.like(SysZoneType::getName, sysZoneType.getName()));
        }
        queryWrapper.orderByDesc(SysZoneType::getCreateTime);
        sysZoneTypeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R add(@RequestBody SysZoneType sysZoneType) {
        if (this.sysZoneTypeService.save(sysZoneType)) {
            return R.success("添加成功！");
        } else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysZoneType sysZoneType) {
        sysZoneTypeService.updateById(sysZoneType);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R delete(@RequestBody SysZoneType sysZoneType) {
        sysZoneTypeService.removeById(sysZoneType);
        return R.success("删除成功！");
    }

    @PutMapping("/status")
    public R change(@RequestBody SysZoneType sysZoneType) {
        sysZoneTypeService.updateById(sysZoneType);
        return R.success(1,"状态已改变!");
    }

    @GetMapping("/list")
    public R list() {
        return R.success(sysZoneTypeService.list());
    }

}

