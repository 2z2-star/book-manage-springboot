package com.test.bookserver.controller;

import com.test.bookserver.config.TokenInterceptor;
import com.test.bookserver.common.Result;
import com.test.bookserver.common.RoleEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    static class LoginUser {
        private String username;
        private String password;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginUser loginUser) {
        if ("admin".equals(loginUser.getUsername()) && "123456".equals(loginUser.getPassword())) {
            // 调用拦截器封装好的方法生成并存储token，不要手动put
            String token = TokenInterceptor.generateToken(RoleEnum.ADMIN);
            return Result.success(200, "登录成功", token);
        }
        return Result.error(400, "用户名或密码错误");
    }
}