package com.destiny.mapper;

import com.destiny.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhukang
 */
@Mapper
public interface CategoryMapper {
    /**
     * 新增分类
     *
     * @param category 分类
     */
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) value " +
            "(#{categoryName}, #{categoryAlias}, #{createUser}, now(), now())")
    void addCategory(Category category);


    /**
     * 查询id下创建的所有分类
     *
     * @param id id
     * @return categories 查询到的分类
     */
    @Select("select * from category where create_user=#{id};")
    List<Category> queryCategoriesByUserId(Integer id);

    /**
     * 查询指定id下的分类详情
     *
     * @param id 分类id
     * @return 分类详情
     */
    @Select("select * from category where id=#{id};")
    Category queryDetailById(Integer id);

    /**
     * 更新分类名称和别名
     *
     * @param category 分类的信息
     */
    @Update("update category set category_alias=#{categoryAlias}, category_name=#{categoryName} where id=#{id};")
    void updateCategory(Category category);


    /**
     * @param category
     */
    @Delete("delete from category where id=#{id}")
    void deleteCategory(Integer id);
}

