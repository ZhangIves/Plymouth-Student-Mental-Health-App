package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loong.x.app.entity.SysNews;
import com.loong.x.app.entity.SysNews;
import com.loong.x.app.service.SysNewsService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysNews)表控制层
 *
 * @author makejava
 * @since 2024-05-11 14:07:09
 */
@RestController
@RequestMapping("sysNews")
public class SysNewsController {
    /**
     * 服务对象
     */
    @Resource
    private SysNewsService sysNewsService;
    /**
     * 分页查询
     *
     * @param news 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public R queryByPage(SysNews news, LXPageRequest pageRequest) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysNews> queryWrapper = new LambdaQueryWrapper<>();
        if (news.getStatus() != null) {
            queryWrapper.eq(SysNews::getStatus, news.getStatus());
        }
        if (StringUtils.hasLength(news.getTitle())) {
            queryWrapper.and(c -> c.like(SysNews::getTitle, news.getTitle()));
        }
        queryWrapper.orderByDesc(SysNews::getCreateTime);
        sysNewsService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R add(@RequestBody SysNews news) {
        if (this.sysNewsService.save(news)) {
            return R.success("添加成功！");
        } else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysNews news) {
        sysNewsService.updateById(news);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R delete(@RequestBody SysNews news) {
        sysNewsService.removeById(news);
        return R.success("删除成功！");
    }

    @PutMapping("/status")
    public R change(@RequestBody SysNews news) {
        sysNewsService.updateById(news);
        return R.success(1,"状态已改变!");
    }

    @GetMapping("/list")
    public R list() {
        return R.success(sysNewsService.list());
    }
}

