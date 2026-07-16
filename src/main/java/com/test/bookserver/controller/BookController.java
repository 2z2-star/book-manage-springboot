package com.test.bookserver.controller;

import com.github.pagehelper.PageInfo;
import com.test.bookserver.entity.Book;
import com.test.bookserver.common.Result;
import com.test.bookserver.common.RoleEnum;
import com.test.bookserver.config.TokenInterceptor;
import com.test.bookserver.service.BookService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    // 注入业务层
    @Resource
    private BookService bookService;
    // 模糊搜索图书分页
    @GetMapping("/search")
    public Result<PageInfo<Book>> search(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author
    ){
        PageInfo<Book> pageData = bookService.searchBookPage(pageNum, pageSize, name, author);
        return Result.success(pageData);
    }

    // 1. 分页查询全部图书（数据库分页，满足你接口<200ms要求）
    @GetMapping("/page")
    public Result<PageInfo<Book>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize
    ){
        PageInfo<Book> pageData = bookService.getBookPage(pageNum, pageSize);
        return Result.success(pageData);
    }

    // 2. 新增图书
    @PostMapping("/add")
    public Result<String> add(@RequestBody Book book) {
        bookService.insertBook(book);
        return Result.success("图书新增成功");
    }

    // 3. 修改图书
    @PutMapping("/update")
    public Result<String> update(@RequestBody Book book) {
        int row = bookService.updateBook(book);
        if(row > 0){
            return Result.success("图书信息修改完成");
        }
        Result<String> res = new Result<>();
        res.setCode(404);
        res.setMsg("该ID图书不存在");
        return res;
    }

    // 4. 删除图书（管理员权限校验）
    @DeleteMapping("/delete")
    public Result<String> delete(@RequestParam Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        var role = TokenInterceptor.tokenMap.get(token);
        if (!RoleEnum.ADMIN.equals(role)) {
            Result<String> res = new Result<>();
            res.setCode(403);
            res.setMsg("权限不足，仅管理员可删除");
            return res;
        }
        int row = bookService.deleteBookById(id);
        if(row > 0){
            return Result.success("图书删除成功");
        }
        Result<String> res = new Result<>();
        res.setCode(404);
        res.setMsg("未找到对应ID图书");
        return res;
    }

    // 5. 批量清空全部图书（仅管理员）
    @DeleteMapping("/clearAll")
    public Result<String> clearAll(HttpServletRequest request) {
        String token = request.getHeader("token");
        var role = TokenInterceptor.tokenMap.get(token);
        if (!RoleEnum.ADMIN.equals(role)) {
            Result<String> res = new Result<>();
            res.setCode(403);
            res.setMsg("权限不足");
            return res;
        }
        bookService.clearAllBook();
        return Result.success("全部图书已清空");
    }
}