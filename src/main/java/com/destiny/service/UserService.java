package com.destiny.service;

import com.destiny.pojo.User;

/**
 * @author zhukang
 */
public interface UserService {
    /**
     * 根据名字查用户信息
     * @param username 用户名
     * @return 查询到的用户
     */
    User findByUserName(String username);

    /**
     * 注册
     */
    void register(String username, String password);

    /**
     * 更新用户信息
     * @param user d
     */
    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
