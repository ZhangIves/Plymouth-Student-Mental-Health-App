package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loong.x.app.entity.SysQuestionLog;
import com.loong.x.app.service.SysQuestionLogService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysQuestionLogLog)表控制层
 *
 * @author makejava
 * @since 2024-05-12 17:19:26
 */
@RestController
@RequestMapping("sysQuestionLog")
public class SysQuestionLogController {
    /**
     * 服务对象
     */
    @Resource
    private SysQuestionLogService sysQuestionLogService;

    /**
     * 分页查询
     *
     * @param sysQuestion 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public R queryByPage(SysQuestionLog sysQuestion, LXPageRequest pageRequest) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysQuestionLog> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(sysQuestion.getContent())) {
            queryWrapper.and(c -> c.like(SysQuestionLog::getContent, sysQuestion.getContent()));
        }
        queryWrapper.orderByDesc(SysQuestionLog::getCreateTime);
        sysQuestionLogService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R add(@RequestBody SysQuestionLog sysQuestion) {
        if (this.sysQuestionLogService.save(sysQuestion)) {
            return R.success("添加成功！");
        } else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysQuestionLog sysQuestion) {
        sysQuestionLogService.updateById(sysQuestion);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R delete(@RequestBody SysQuestionLog sysQuestion) {
        sysQuestionLogService.removeById(sysQuestion);
        return R.success("删除成功！");
    }

    @PutMapping("/status")
    public R change(@RequestBody SysQuestionLog sysQuestion) {
        sysQuestionLogService.updateById(sysQuestion);
        return R.success(1,"状态已改变!");
    }

    @GetMapping("/list")
    public R list() {
        return R.success(sysQuestionLogService.list());
    }

}

