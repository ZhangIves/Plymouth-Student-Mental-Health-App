package com.loong.x.uniapp.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/16 10:14
 */
@Data
public class FileVo implements Serializable {

    private static final long serialVersionUID = -6805582643001421717L;

    /**
     * 原文件名
     */
    private String originalFilename;

    /**
     * 现文件名
     */
    private String nowFilename;

    /**
     * 扩展名
     */
    private String extName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 资源路径
     */
    private String url;

}
