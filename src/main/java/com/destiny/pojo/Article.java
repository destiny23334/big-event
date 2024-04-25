package com.destiny.pojo;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Data
public class Article {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;
    private String content; //文章内容
    private String coverImg; //封面图像
    private String state; //发布状态 已发布|草稿
    private Integer categoryId; //文章分类id
    private Integer createUser; //创建人ID
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //更新时间
}
