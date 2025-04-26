package com.loong.x.uniapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.x.app.entity.EvaluationAnswer;
import com.loong.x.app.service.EvaluationAnswerService;
import com.loong.x.common.custom.LXPageRequest;
import com.loong.x.common.custom.PaginationResult;
import com.loong.x.uniapp.dto.EvaluationAnswerDto;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2025/4/2 17:41
 */
@RestController
@RequestMapping("/clientApi/evaluation")
public class EvaluationController extends BaseController {
    @Resource
    private EvaluationAnswerService evaluationAnswerService;

    @GetMapping("/page")
    public ResponseObject page(HttpServletRequest request, LXPageRequest page) {
        String token = request.getHeader("Access-Token");
        if (token.equals("")){
            return getFailureResult(0,"请先登录！");
        }
        EvaluationAnswer evaluationAnswer = new EvaluationAnswer();
        evaluationAnswer.setUserId(token);
        Sort.Direction direction = Sort.Direction.DESC;
        String sort = "createTime,desc";
        Sort sortObject = Sort.by(direction, sort.split(",")[0]);
        PageRequest pageRequest1 = PageRequest.of(page.getPage()-1, page.getPageSize(), sortObject);
        PaginationResult<EvaluationAnswerDto> evaluationAnswerDtoPaginationResult = evaluationAnswerService.queryAllByPageLimit(evaluationAnswer, pageRequest1);

        Page pageInfo = new Page(page.getPage(), page.getPageSize());
        LambdaQueryWrapper<EvaluationAnswer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EvaluationAnswer::getUserId,token);

        queryWrapper.orderByDesc(EvaluationAnswer::getCreateTime);
        Page page1 = evaluationAnswerService.page(pageInfo, queryWrapper);
        Map<String, Object> outParams = new HashMap();
        outParams.put("content", evaluationAnswerDtoPaginationResult.getRecords());
        outParams.put("pageSize", evaluationAnswerDtoPaginationResult.getPageSize());
        outParams.put("pageNumber", evaluationAnswerDtoPaginationResult.getCurrentPage());
        outParams.put("totalRow", evaluationAnswerDtoPaginationResult.getTotalPages());
        outParams.put("totalPage", evaluationAnswerDtoPaginationResult.getTotalPages());
        return getSuccessResult(outParams);
    }

}
