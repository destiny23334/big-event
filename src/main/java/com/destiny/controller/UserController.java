package com.destiny.controller;


import com.destiny.pojo.Result;
import com.destiny.pojo.User;
import com.destiny.service.UserService;
import com.destiny.utils.JWTUtils;
import com.destiny.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.destiny.utils.JWTUtils.parseToken;

/**
 * @author zhukang
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}$") String username,
                                   @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findByUserName(username);
        if (user == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被注册");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findByUserName(username);
        if (user == null) {
            return Result.error("用户名错误");
        } else if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return Result.error("密码错误");
        }
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());

        String token = JWTUtils.getToken(claims);
        return Result.success(token);
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result<String> update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success("更新成功");
    }

    @PatchMapping("/updateAvatar")
    public Result<String> updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success("更新成功");
    }

    @PatchMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = DigestUtils.md5DigestAsHex(params.get("old_pwd").getBytes());
        String newPwd = DigestUtils.md5DigestAsHex(params.get("new_pwd").getBytes());
        String checkPwd = DigestUtils.md5DigestAsHex(params.get("re_pwd").getBytes());

        // 1. 验证一下
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        String username = (String) claims.get("username");
        String dbPwd = userService.findByUserName(username).getPassword();
        if (!oldPwd.equals(dbPwd)) {
            return Result.error("原密码错误");
        } else if (!newPwd.equals(checkPwd)) {
            return Result.error("两次密码不一致");
        }

        userService.updatePwd(newPwd);
        return Result.success("更新成功");


    }
}
