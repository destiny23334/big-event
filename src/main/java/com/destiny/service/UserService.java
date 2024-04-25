package com.destiny.service;

import com.destiny.pojo.User;

/**
 * @author zhukang
 */
public interface UserService {
    /**
     *
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 注册
     */
    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
