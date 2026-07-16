package com.test.bookserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 图书馆管理系统启动类
 * 放置在根包com.test.bookserver下，自动扫描所有子包的组件
 */
@SpringBootApplication
@MapperScan("com.test.bookserver.mapper") // 扫描mapper接口包，MyBatis必备
@EnableTransactionManagement // 开启事务回滚功能
public class BookserverApplication {

    public static void main(String[] args) {
        // 启动Spring Boot应用
        SpringApplication.run(BookserverApplication.class, args);
        System.out.println("==================== 图书馆管理系统启动成功 ====================");
    }
}

