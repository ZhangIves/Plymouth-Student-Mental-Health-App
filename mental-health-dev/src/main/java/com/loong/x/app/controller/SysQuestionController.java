package com.loong.x.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loong.x.app.entity.SysQuestion;
import com.loong.x.app.entity.SysQuestion;
import com.loong.x.app.service.SysQuestionService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.utils.R;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysQuestion)表控制层
 *
 * @author makejava
 * @since 2024-05-12 17:19:26
 */
@RestController
@RequestMapping("sysQuestion")
public class SysQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private SysQuestionService sysQuestionService;

    /**
     * 分页查询
     *
     * @param sysQuestion 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public R queryByPage(SysQuestion sysQuestion, LXPageRequest pageRequest) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageRequest.getPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<SysQuestion> queryWrapper = new LambdaQueryWrapper<>();
        if (sysQuestion.getStatus() != null) {
            queryWrapper.eq(SysQuestion::getStatus, sysQuestion.getStatus());
        }
        if(sysQuestion.getZoneId()!=null){
            queryWrapper.eq(SysQuestion::getZoneId,sysQuestion.getZoneId());
        }
        if (StringUtils.hasLength(sysQuestion.getTitle())) {
            queryWrapper.and(c -> c.like(SysQuestion::getTitle, sysQuestion.getTitle()));
        }
        queryWrapper.orderByDesc(SysQuestion::getCreateTime);
        sysQuestionService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R add(@RequestBody SysQuestion sysQuestion) {
        if (this.sysQuestionService.save(sysQuestion)) {
            return R.success("添加成功！");
        } else {
            return R.error("操作失败");
        }
    }

    @PutMapping
    public R update(@RequestBody SysQuestion sysQuestion) {
        sysQuestionService.updateById(sysQuestion);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R delete(@RequestBody SysQuestion sysQuestion) {
        sysQuestionService.removeById(sysQuestion);
        return R.success("删除成功！");
    }

    @PutMapping("/status")
    public R change(@RequestBody SysQuestion sysQuestion) {
        sysQuestionService.updateById(sysQuestion);
        return R.success(1,"状态已改变!");
    }

    @GetMapping("/list")
    public R list() {
        return R.success(sysQuestionService.list());
    }

}

