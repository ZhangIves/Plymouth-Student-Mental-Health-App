package com.loong.x.common.custom;

import lombok.Data;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/10 22:34
 */
@Data
public class LXPageRequest {
    /**
     * 页面
     */
    private int page;
    /**
     * 分页大小
     */
    private int pageSize;
}
