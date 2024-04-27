package com.destiny.controller;

import com.destiny.pojo.Category;
import com.destiny.pojo.Result;
import com.destiny.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhukang
 */
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public Result<List<Category>> getCategory() {
        List<Category> categories = categoryService.getCategories();
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.debug(categories.toString());
        return Result.success(categories);
    }

    @PostMapping
    public Result<String> addCategory(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.addCategory(category);
        return Result.success("新增成功");
    }

    @PutMapping
    public Result<String> updateCategory(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.updateCategory(category);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteCategory(@RequestParam Integer id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Category> getDetail(@RequestParam Integer id) {
        Category category = categoryService.getDetail(id);
        return Result.success(category);
    }

}
