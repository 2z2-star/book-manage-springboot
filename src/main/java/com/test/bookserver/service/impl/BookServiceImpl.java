package com.test.bookserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.bookserver.entity.Book;
import com.test.bookserver.mapper.BookMapper;
import com.test.bookserver.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public PageInfo<Book> searchBookPage(Integer pageNum, Integer pageSize, String name, String author) {
        PageHelper.startPage(pageNum, pageSize);
        List<Book> list = bookMapper.selectByLike(name, author);
        return new PageInfo<>(list);
    }

    @Resource
    private BookMapper bookMapper;

    @Override
    public PageInfo<Book> getBookPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Book> list = bookMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBook(Book book) {
        bookMapper.insert(book);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBook(Book book) {
        return bookMapper.updateById(book);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBookById(Long id) {
        return bookMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void clearAllBook() {
        bookMapper.deleteAll();
    }
}