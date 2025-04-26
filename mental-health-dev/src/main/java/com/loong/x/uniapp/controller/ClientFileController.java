package com.loong.x.uniapp.controller;

import com.loong.x.app.service.SysImageService;
import com.loong.x.common.utils.FileUtil;
import com.loong.x.uniapp.utils.BaseController;
import com.loong.x.uniapp.utils.ResponseObject;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传管理控制类
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/clientApi/file")
public class ClientFileController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ClientFileController.class);

    /**
     * 系统环境变量
     * */
    private Environment env;

    /**
     * 系统设置服务接口
     * */
    @Resource
    private SysImageService sysImageService;

    /**
     * 上传文件
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseObject upload1(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        String sourcePic = request.getParameter("file");
        MultipartFile file = multipartRequest.getFile("file");

        Map<String, String> resultMap = new HashMap<>();
        try {
            HashMap<String, String> stringStringHashMap = saveFile1(file);
            String fileName = stringStringHashMap.get("name");
            String filePath = stringStringHashMap.get("path");
            resultMap.put("status", "success");
            resultMap.put("domain", "http://localhost:8091/uploadImages/");
            resultMap.put("filePath", filePath);
            resultMap.put("fileName", fileName);
            resultMap.put("state", "SUCCESS");
            resultMap.put("original", file.getOriginalFilename());
            resultMap.put("size", file.getSize()+"");
            resultMap.put("title", fileName);
            resultMap.put("type", file.getContentType());
            resultMap.put("url", "url");
        } catch (Exception e) {
            return getFailureResult(201, "上传失败，请检查上传配置及权限");
        }
        return getSuccessResult(resultMap);
    }

    public HashMap<String, String> saveFile1(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String imageName = fileName.substring(fileName.lastIndexOf("."));
        String pathRoot = env.getProperty("images.root");
        if (pathRoot == null || StringUtils.isEmpty(pathRoot)) {
            pathRoot = ResourceUtils.getURL("classpath:").getPath();
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        String baseImage = env.getProperty("pictureFile.path-mapping");
        HashMap<String, String> upload = FileUtil.upload(file, pathRoot + baseImage);
        return upload;
    }
}
