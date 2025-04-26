package com.loong.x.uniapp.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 文章列表请求参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleListParam extends PageParam implements Serializable {
    private String title;
}
