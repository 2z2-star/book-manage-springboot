package com.test.bookserver.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * 图书实体类
 */
@Data
public class Book implements Serializable {
    // 图书ID
    private Long id;
    // 图书名称
    private String name;
    // 作者
    private String author;
    // 出版社
    private String press;
    // 图书分类（新增字段）
    private String category;
    // 库存数量
    private Integer stock;
}
