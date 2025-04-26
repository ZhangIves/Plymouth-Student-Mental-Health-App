package com.loong.x.app.controller;

import com.loong.x.common.utils.FileUtil;
import com.loong.x.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/3/23 12:10
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private Environment environment;


    @GetMapping("/read/file")
    public Map<String,Object> readInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("port",environment.getProperty("server.port"));
        map.put("port2",environment.getProperty("pictureFile.path")+environment.getProperty("pictureFile.path-mapping"));
        map.put("appid",environment.getProperty("alipay.pay.appid"));
        map.put("notify",environment.getProperty("alipay.pay.notify"));
        map.put("javaversion",environment.getProperty("java.version"));
        map.put("javahome",environment.getProperty("JAVA_HOME"));
        map.put("mavenhome",environment.getProperty("MAVEN_HOME"));
//        System.out.println(map);
        return  map;
    }



    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        String pathRoot = environment.getProperty("images.root");
        if (pathRoot == null || StringUtils.isEmpty(pathRoot)) {
            pathRoot = ResourceUtils.getURL("classpath:").getPath();
        }
        String property = environment.getProperty("upload.image.path");
        String baseImage = environment.getProperty("pictureFile.path-mapping");
        return R.success(FileUtil.upload(file,pathRoot+baseImage));
    }

    @PostMapping("/uploadWMImages")
    public R uploadImages(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        String pathRoot = environment.getProperty("images.root");
        if (pathRoot == null || StringUtils.isEmpty(pathRoot)) {
            pathRoot = ResourceUtils.getURL("classpath:").getPath();
        }
        String baseImage = environment.getProperty("pictureFile.path-mapping");
        return R.success(FileUtil.upload1(file,pathRoot+baseImage));
    }

    @GetMapping("/image")
    public ResponseEntity<InputStreamResource> getImage() throws IOException {
        // 图片文件的路径
        String imagePath = "classpath:static/images/your-image.jpg";

        // 获取图片文件的输入流
        InputStream inputStream = getClass().getResourceAsStream(imagePath);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        // 设置HTTP响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=your-image.jpg");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        // 返回图片文件流
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(inputStreamResource);


    }

    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/images/" + imageName);
        byte[] imageBytes = IOUtils.toByteArray(resource.getInputStream());
        return ResponseEntity.ok().body(imageBytes);
    }

}
