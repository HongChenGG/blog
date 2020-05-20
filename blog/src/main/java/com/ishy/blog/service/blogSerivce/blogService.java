package com.ishy.blog.service.blogSerivce;

import com.ishy.blog.po.TBlog;
import com.ishy.blog.query.FirstPageBlog;
import com.ishy.blog.query.blogQuery;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;
import java.util.Map;

/**
 * @author 红尘
 * @Date 2020/5/16 15:29
 */
public interface blogService {

    List<FirstPageBlog> getAllFirstPageBlog();
    TBlog getBlog(Long id);
    List<TBlog>listBlog(blogQuery blog);
    int saveBlog(TBlog blog);
    int update(Long id,TBlog blog) throws NotFoundException;
    void delete(Long id);
    List<TBlog>selectAll();
    void saveBolgTag(TBlog blog);
    TBlog getBlogs(Long id);
    TBlog getBlogBytitle(String title);

    void deleteBlogTags(Long id);

    List<TBlog> selectAllandRecommend();

    List<TBlog> selectOneLimit(String query);

    TBlog getBlogsAndContent(Long id);

    List<TBlog> getBlogsByTypes(Long id);

    List<TBlog> getBlogsByTags(Long id);

    Map<String, List<TBlog>> archiveBlog();

    Long countBlog();
}
