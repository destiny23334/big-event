package com.destiny.service;

import com.destiny.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zhukang
 */
public interface CategoryService {

    /**
     * 新增分类
     *
     * @param category 分类
     */
    void addCategory(Category category);

    /**
     * 查询分类
     *
     * @return 查询的所有分类
     */
    List<Category> getCategories();


    /**
     * 查询详情
     *
     * @param id 查询的id
     * @return 详情
     */
    Category getDetail(Integer id);

    /**
     * 更新详情
     *
     * @param category 详情信息
     */
    void updateCategory(Category category);

    /**
     * 删除分类
     *
     * @param id 要删除的id
     */
    void deleteCategory(Integer id);
}
