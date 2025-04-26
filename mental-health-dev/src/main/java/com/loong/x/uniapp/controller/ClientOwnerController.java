package com.loong.x.uniapp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loong.x.app.entity.*;
import com.loong.x.app.mapper.UserMapper;
import com.loong.x.app.service.*;
import com.loong.x.app.vo.UserVo;
import com.loong.x.common.utils.TimeUtils;
import com.loong.x.uniapp.dto.SysOwnerDto;
import com.loong.x.uniapp.exception.BusinessCheckException;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/3/19 15:11
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/clientApi/owner")
public class ClientOwnerController extends BaseController {

    @Resource
    private SysOwnerService sysOwnerService;
    @Resource
    private SysImageService sysImageService;


    @PostMapping("/save")
    public ResponseObject add(HttpServletRequest request, @RequestBody SysOwnerDto dto) {
        SysOwner owner = dto.getOwner();
        List<SysImage> imagePaths = dto.getSysImageList();
        String token = request.getHeader("Access-Token");
        owner.setOwnerId(token);
        List<SysImage> images = new ArrayList<>();
        if(sysOwnerService.saveOrUpdate(owner)){
            if (imagePaths.size()>0){
                for (SysImage s : imagePaths) {
                    s.setRepairId(owner.getId().toString());
                    images.add(s);
                }
                sysImageService.saveBatch(imagePaths);
            }
            return getSuccessResult(owner);
        }
        return getFailureResult(0,"操作失败");
    }

    @GetMapping("/detail")
    public ResponseObject detail(HttpServletRequest request){
        String token = request.getHeader("Access-Token");
        LambdaQueryWrapper<SysOwner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysOwner::getOwnerId,token);
        return getSuccessResult(sysOwnerService.getOne(queryWrapper));

    }

    @GetMapping("/image")
    public ResponseObject getImage(HttpServletRequest request,String sysOId){
        String token = request.getHeader("Access-Token");
        LambdaQueryWrapper<SysImage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysImage::getRepairId,sysOId);
        return getSuccessResult(sysImageService.list(queryWrapper));

    }

    @DeleteMapping("/image")
    public ResponseObject deleteImage(HttpServletRequest request,@RequestBody SysImage image){
        SysImage byId = sysImageService.getById(image.getId());
        if (byId == null){
            return getFailureResult(0,"操作失败！");
        }else {
            sysImageService.removeById(byId);
            return getSuccessResult("删除成功",null);
        }
    }

}
