package com.loong.x.app.controller;

import com.loong.x.app.entity.QuestionAnswer;
import com.loong.x.app.service.QuestionAnswerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (QuestionAnswer)表控制层
 *
 * @author makejava
 * @since 2025-04-08 11:07:28
 */
@RestController
@RequestMapping("questionAnswer")
public class QuestionAnswerController {
    /**
     * 服务对象
     */
    @Resource
    private QuestionAnswerService questionAnswerService;

    /**
     * 分页查询
     *
     * @param questionAnswer 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<QuestionAnswer>> queryByPage(QuestionAnswer questionAnswer, PageRequest pageRequest) {
        return ResponseEntity.ok(this.questionAnswerService.queryByPage(questionAnswer, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<QuestionAnswer> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.questionAnswerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param questionAnswer 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<QuestionAnswer> add(QuestionAnswer questionAnswer) {
        return ResponseEntity.ok(this.questionAnswerService.insert(questionAnswer));
    }

    /**
     * 编辑数据
     *
     * @param questionAnswer 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<QuestionAnswer> edit(QuestionAnswer questionAnswer) {
        return ResponseEntity.ok(this.questionAnswerService.update(questionAnswer));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.questionAnswerService.deleteById(id));
    }

}

