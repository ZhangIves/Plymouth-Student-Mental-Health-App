package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.SysEms;
import com.loong.x.app.entity.SysEms;
import com.loong.x.app.service.SysEmsService;
import com.loong.x.common.utils.R;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysEms)表控制层
 *
 * @author makejava
 * @since 2024-04-26 15:49:36
 */
@RestController
@RequestMapping("sysEms")
public class SysEmsController {
    /**
     * 服务对象
     */
    @Resource
    private SysEmsService sysEmsService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(page, pageSize);
        LambdaQueryWrapper<SysEms> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.hasLength(name)) {
            queryWrapper.and(c -> c.like(SysEms::getName, name));
        }
        queryWrapper.orderByAsc(SysEms::getCreateTime);
        sysEmsService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }
    @GetMapping("/id")
    public R gerSysEms(Integer id) {
        return R.success(this.sysEmsService.getById(id));
    }


    @PostMapping
    public R add(@RequestBody SysEms ems) {
        if (this.sysEmsService.save(ems)){
            return R.success("添加成功！");
        }else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysEms ems) {
        sysEmsService.updateById(ems);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R delete(@RequestBody SysEms ems) {
        sysEmsService.removeById(ems);
        return R.success("删除成功！");
    }

}

