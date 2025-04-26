package com.loong.x.app.controller;

import com.loong.x.app.entity.MentalHealthEvaluation;
import com.loong.x.app.service.MentalHealthEvaluationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Mental Health Evaluation Scoring Criteria(MentalHealthEvaluation)表控制层
 *
 * @author makejava
 * @since 2025-04-08 11:07:29
 */
@RestController
@RequestMapping("mentalHealthEvaluation")
public class MentalHealthEvaluationController {
    /**
     * 服务对象
     */
    @Resource
    private MentalHealthEvaluationService mentalHealthEvaluationService;

    /**
     * 分页查询
     *
     * @param mentalHealthEvaluation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MentalHealthEvaluation>> queryByPage(MentalHealthEvaluation mentalHealthEvaluation, PageRequest pageRequest) {
        return ResponseEntity.ok(this.mentalHealthEvaluationService.queryByPage(mentalHealthEvaluation, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MentalHealthEvaluation> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.mentalHealthEvaluationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param mentalHealthEvaluation 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MentalHealthEvaluation> add(MentalHealthEvaluation mentalHealthEvaluation) {
        return ResponseEntity.ok(this.mentalHealthEvaluationService.insert(mentalHealthEvaluation));
    }

    /**
     * 编辑数据
     *
     * @param mentalHealthEvaluation 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MentalHealthEvaluation> edit(MentalHealthEvaluation mentalHealthEvaluation) {
        return ResponseEntity.ok(this.mentalHealthEvaluationService.update(mentalHealthEvaluation));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.mentalHealthEvaluationService.deleteById(id));
    }

}

