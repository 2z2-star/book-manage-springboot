package com.test.bookserver.mapper;
import org.apache.ibatis.annotations.Param;
import com.test.bookserver.entity.Book;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    // 分页查询所有图书
    List<Book> selectAll();

    // 新增
    int insert(Book book);
    // 修改
    int updateById(Book book);
    // 根据id删除
    int deleteById(Long id);
    // 清空全部
    int deleteAll();
    List<Book> selectByLike(@Param("name") String name, @Param("author") String author);
}
