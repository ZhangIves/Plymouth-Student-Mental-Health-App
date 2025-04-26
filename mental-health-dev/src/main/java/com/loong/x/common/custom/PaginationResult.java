package com.loong.x.common.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/3/28 21:07
 */
@Data
@AllArgsConstructor
public class PaginationResult<T> {
    private int totalCount;  // 总记录数
    private int currentPage; // 当前页码
    private int pageSize;    // 每页数量
    private int totalPages;  // 总页数
    private List<T> records;
}
