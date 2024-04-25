package com.destiny.mapper;

import com.destiny.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhukang
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查找用户
     * @param username 查询的用户名
     * @return 找到的用户信息，null表示没找到
     */
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    /**
     * 插入一条用户信息
     * @param username 用户名
     * @param password 密码
     */
    @Insert("insert into user(username, password, create_time, update_time) " +
            "values(#{username}, #{password}, now(), now())")
    void add(String username, String password);

    @Update("update user set nickname=#{nickname}, email=#{email}, user_pic=#{userPic}, update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatarPath}, update_time=now() where id=#{id}")
    void updateAvatar(String avatarPath, Integer id);

    @Update("update user set password=#{newPwd}, update_time=now() where id=#{id}")
    void updatePwd(String newPwd, Integer id);
}
