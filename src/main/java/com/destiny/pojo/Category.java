package com.destiny.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Data
public class Category {
    /**
     * 主键ID
     */
    @NotNull(groups =  Update.class)
    private Integer id;

    /**
     * 分类名称
     */
    @NotEmpty
    private String categoryName;

    /**
     * 分类别名
     */
    @NotEmpty
    private String categoryAlias;

    /**
     * 创建人ID
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }



}
