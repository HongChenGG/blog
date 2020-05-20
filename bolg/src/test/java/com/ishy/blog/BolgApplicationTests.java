package com.ishy.blog;



import com.ishy.blog.mapper.TBlogMapper;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TType;
import com.ishy.blog.query.blogQuery;
import com.ishy.blog.service.blogSerivce.blogServiceImpl;
import com.ishy.blog.service.typeService.TypeServiceImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BolgApplicationTests {
    @Autowired
    blogServiceImpl userService;
    @Test
    void contextLoads() {
        blogQuery query = new blogQuery();
            query.setTitlekey("人");
        List<TBlog> type = userService.listBlog(query);
        for (TBlog tType : type) {
            System.out.println(tType);
        }
    }

}
