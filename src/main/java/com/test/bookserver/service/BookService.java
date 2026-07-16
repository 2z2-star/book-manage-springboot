package com.test.bookserver.service;

import com.github.pagehelper.PageInfo;
import com.test.bookserver.entity.Book;
import java.util.List;

public interface BookService {
    // 分页查询全部图书
    PageInfo<Book> getBookPage(Integer pageNum, Integer pageSize);

    // 新增：书名+作者模糊搜索分页
    PageInfo<Book> searchBookPage(Integer pageNum, Integer pageSize, String name, String author);

    // 新增图书
    void insertBook(Book book);
    // 修改图书
    int updateBook(Book book);
    // 根据id删除
    int deleteBookById(Long id);
    // 清空所有图书
    void clearAllBook();
}