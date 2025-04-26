package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loong.x.app.entity.SysNewsType;
import com.loong.x.app.entity.SysZone;
import com.loong.x.app.entity.SysZoneLog;
import com.loong.x.app.service.SysNewsTypeService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysNewsType)表控制层
 *
 * @author makejava
 * @since 2024-05-10 22:30:22
 */
@RestController
@RequestMapping("sysNewsType")
public class SysNewsTypeController {
    /**
     * 服务对象
     */
    @Resource
    private SysNewsTypeService sysNewsTypeService;

    /**
     * 分页查询
     *
     * @param sysNewsType 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public R queryByPage(SysNewsType sysNewsType, LXPageRequest pageRequest) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysNewsType> queryWrapper = new LambdaQueryWrapper<>();
        if (sysNewsType.getStatus() != null) {
            queryWrapper.eq(SysNewsType::getStatus, sysNewsType.getStatus());
        }
        if (StringUtils.hasLength(sysNewsType.getName())) {
            queryWrapper.and(c -> c.like(SysNewsType::getName, sysNewsType.getName()));
        }
        queryWrapper.orderByDesc(SysNewsType::getCreateTime);
        sysNewsTypeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R add(@RequestBody SysNewsType newsType) {
        if (this.sysNewsTypeService.save(newsType)) {
            return R.success("添加成功！");
        } else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysNewsType newsType) {
        sysNewsTypeService.updateById(newsType);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R delete(@RequestBody SysNewsType newsType) {
        sysNewsTypeService.removeById(newsType);
        return R.success("删除成功！");
    }

    @PutMapping("/status")
    public R change(@RequestBody SysNewsType newsType) {
        sysNewsTypeService.updateById(newsType);
        return R.success(1,"状态已改变!");
    }

    @GetMapping("/list")
    public R list() {
        return R.success(sysNewsTypeService.list());
    }


}

