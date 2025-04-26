package com.loong.x.app.controller;

import com.loong.x.common.utils.FileUtil;
import com.loong.x.common.utils.R;
import com.loong.x.common.utils.TimeUtils;
import com.loong.x.common.utils.WaterMarkUtils;
import lombok.AllArgsConstructor;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传管理控制类
 * <p>
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/file")
public class BackendFileController {

    @PostMapping("/user/avatar")
    public R uploadAvatarHandler(@RequestParam("avatar") MultipartFile uploadFile) throws IOException {
        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(path + "/static/avatar/user");
        if (!uploadDir.exists()) {
            System.out.println("上传头像路径不存在，正在创建...");
            uploadDir.mkdir();
        }
        if (uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            //我的文件保存在static目录下的avatar/user
            File avatar = new File(path + "/static/avatar/user/", oldName);
            try {
                //保存图片
                uploadFile.transferTo(avatar);
                //返回成功结果，附带文件的相对路径
                return R.success(1,"上传成功", "/avatar/user/" + oldName);
            } catch (IOException e) {
                e.printStackTrace();
                return R.error("上传失败");
            }
        } else {
            System.out.println("上传的文件为空");
            return R.error("文件传输错误");
        }

    }



    @PostMapping("/uploadImages")
    public R uploadImagesHandler(@RequestParam("avatar") MultipartFile uploadFile) throws IOException {
        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        File uploadDir = new File(path + "/static/uploadImages/");
        if (!uploadDir.exists()) {
            System.out.println("上传路径不存在，正在创建...");
            uploadDir.mkdir();
        }
        if (uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            //我的文件保存在static目录下的avatar/user
            String fileType = FileUtil.getExtensionName(uploadFile.getOriginalFilename());
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssS");
            String nowStr = format.format(date);
            String saveName = nowStr + '.' + fileType;
            System.out.println("[保存的文件名]：" + saveName);
            File avatar = new File(path + "/static/uploadImages/", saveName);
            try {
                //保存图片
                uploadFile.transferTo(avatar);
                //返回成功结果，附带文件的相对路径
                return R.success(1,"上传成功", "/uploadImages/" + saveName);
            } catch (IOException e) {
                e.printStackTrace();
                return R.error("上传失败");
            }
        } else {
            System.out.println("上传的文件为空");
            return R.error("文件传输错误");
        }

    }

    @PostMapping("/uploadImages12")
    public R uploadImagesHandler12(@RequestParam("avatar") MultipartFile uploadFile) throws IOException {
        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        File uploadDir = new File(path + "/static/uploadImages/");
        if (!uploadDir.exists()) {
            System.out.println("上传路径不存在，正在创建...");
            uploadDir.mkdir();
        }
        if (uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            //我的文件保存在static目录下的avatar/user
            String fileType = FileUtil.getExtensionName(uploadFile.getOriginalFilename());
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssS");
            String nowStr = format.format(date);
            String saveName = nowStr + '.' + fileType;
            String saveName1 = nowStr + "-watermarked-" + '.' + fileType;
            System.out.println("[保存的文件名]：" + saveName);
            File avatar = new File(path + "/static/uploadImages/", saveName);
            String path1 = path + "/static/uploadImages/"  + saveName;
            File destImageFile = new File(path + "/static/uploadImages/" +saveName1);
            String watermarkText = "WATERMARK"; // 水印文字

//            WaterMarkUtils.addTextWatermark(watermarkText, avatar, destImageFile);
            try {
                //保存图片
                uploadFile.transferTo(avatar);
                //返回成功结果，附带文件的相对路径
                return R.success(1,"上传成功", "/uploadImages/" + saveName);
            } catch (IOException e) {
                e.printStackTrace();
                return R.error("上传失败");
            }
        } else {
            System.out.println("上传的文件为空");
            return R.error("文件传输错误");
        }

    }

    @PostMapping("/uploadImages1")
    public R uploadImagesHandler1(@RequestParam("avatar") MultipartFile uploadFile) throws IOException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        path = "E:\\data";
        File uploadDir = new File(path + "/Fitness/uploadImages/");
        if (!uploadDir.exists()) {
            System.out.println("上传路径不存在，正在创建...");
            uploadDir.mkdir();
        }
        if (uploadFile != null) {
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            File avatar = new File(path + "/static/uploadImages/", TimeUtils.getTimestamp()+oldName);
            try {
                uploadFile.transferTo(avatar);
                return R.success(1,"上传成功", "/uploadImages/" + TimeUtils.getTimestamp()+oldName);
            } catch (IOException e) {
                e.printStackTrace();
                return R.error("上传失败");
            }
        } else {
            System.out.println("上传的文件为空");
            return R.error("文件传输错误");
        }

    }


}
