package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysShops;
import com.loong.x.app.entity.SysShops;
import com.loong.x.app.service.SysShopsService;
import com.loong.x.common.utils.R;
import com.loong.x.common.utils.TimeUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Time;

/**
 * (SysShops)表控制层
 *
 * @author makejava
 * @since 2024-04-27 09:46:22
 */
@RestController
@RequestMapping("sysShops")
public class SysShopsController {
    /**
     * 服务对象
     */
    @Resource
    private SysShopsService sysShopsService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String status, String owner, Integer type) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(page, pageSize);

        LambdaQueryWrapper<SysShops> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(StringUtils.hasLength(status), SysShops::getShopsStatus, status);

        if (type != null) {
            queryWrapper.eq(SysShops::getType, type);
        }
        if (StringUtils.hasLength(owner)) {
            queryWrapper.and(c -> c.like(SysShops::getOwnerId, owner));
        }
        queryWrapper.orderByAsc(SysShops::getCreateTime);
        sysShopsService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R add(@RequestBody SysShops shops) {
        shops.setId(TimeUtils.getTimestamp());
        if (this.sysShopsService.save(shops)) {
            return R.success("添加成功！");
        } else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysShops shops) {
        sysShopsService.updateById(shops);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R delete(@RequestBody SysShops shops) {
        sysShopsService.removeById(shops);
        return R.success("删除成功！");
    }


}

