package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysBanner;
import com.loong.x.app.service.SysBannerService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (SysSysBanner)表控制层
 *
 * @author makejava
 * @since 2024-05-11 10:17:19
 */
@RestController
@RequestMapping("sysBanner")
public class SysBannerController {
    /**
     * 服务对象
     */
    @Resource
    private SysBannerService bannerService;

    @GetMapping
    public R<com.baomidou.mybatisplus.extension.plugins.pagination.Page> page(LXPageRequest pageRequest, String keyword) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysBanner> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(keyword)) {
            queryWrapper.and(c -> c.like(SysBanner::getTitle, keyword));
        }
        queryWrapper.orderByDesc(SysBanner::getCreateTime);
        bannerService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }


    @PostMapping
    public R<SysBanner> addUser(HttpServletRequest request, @RequestBody SysBanner banner) {
        bannerService.save(banner);
        return R.success(banner);
    }


    @PutMapping
    public R<String> update(@RequestBody SysBanner banner) {
        bannerService.updateById(banner);
        return R.success("修改成功！");
    }

    @PutMapping("change")
    public R<String> change(@RequestBody SysBanner banner) {
        bannerService.updateById(banner);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R<String> delete(@RequestBody SysBanner banner) {
        bannerService.removeById(banner);
        return R.success("删除成功！");
    }

    @GetMapping("/list")
    public R<List<SysBanner>> getAll() {
        return R.success(bannerService.list());
    }

    @PutMapping("status")
    public R<String> changeStatus(@RequestBody SysBanner banner) {
        bannerService.updateById(banner);
        return R.success("修改成功！");
    }

}

