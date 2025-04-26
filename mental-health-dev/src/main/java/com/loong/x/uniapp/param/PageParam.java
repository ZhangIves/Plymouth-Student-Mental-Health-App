package com.loong.x.uniapp.param;

import java.io.Serializable;

/**
 * 分页请求参数
 *
 * Created by FSQ
 * CopyRight https://www.fuint.cn
 */
public class PageParam implements Serializable {
    private static final long serialVersionUID = -1833130751169582924L;

    private Integer page = 1;

    private int pageSize = 20;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序方式
     */
    private String order;

    public PageParam() {
        // empty
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

