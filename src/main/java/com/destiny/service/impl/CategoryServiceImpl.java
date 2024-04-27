package com.destiny.service.impl;

import com.destiny.mapper.CategoryMapper;
import com.destiny.pojo.Category;
import com.destiny.service.CategoryService;
import com.destiny.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhukang
 */
@Service

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(Category category) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        category.setCreateUser(id);
        categoryMapper.addCategory(category);
    }

    @Override
    public List<Category> getCategories() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        return categoryMapper.queryCategoriesByUserId(id);
    }

    @Override
    public Category getDetail(Integer id) {
        return categoryMapper.queryDetailById(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }

}
