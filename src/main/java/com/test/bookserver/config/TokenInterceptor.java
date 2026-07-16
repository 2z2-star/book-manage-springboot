package com.test.bookserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bookserver.common.Result;
import com.test.bookserver.common.RoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    public static Map<String, RoleEnum> tokenMap = new HashMap<>();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 已移除所有uri放行判断，统一由WebConfig配置放行
        String token = request.getHeader("token");
        if (token == null || !tokenMap.containsKey(token)) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Result<Object> result = new Result<>();
            result.setCode(401);
            result.setMsg("未登录或token已失效");
            mapper.writeValue(response.getWriter(), result);
            return false;
        }
        return true;
    }

    public static String generateToken(RoleEnum role) {
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, role);
        return token;
    }
}