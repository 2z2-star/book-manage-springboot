package com.test.bookserver.entity;

import com.test.bookserver.common.RoleEnum;
import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private RoleEnum role;
}