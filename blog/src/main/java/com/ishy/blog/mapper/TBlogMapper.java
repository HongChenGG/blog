package com.ishy.blog.mapper;

import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TTag;
import com.ishy.blog.query.FirstPageBlog;
import com.ishy.blog.query.blogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TBlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TBlog record);

    int insertSelective(TBlog record);

    TBlog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TBlog record);

    int updateByPrimaryKey(TBlog record);

    List<TBlog> selectAll(blogQuery blog);
    List<TBlog> selectAlll();

    void insertBolgTag(@Param("id") Long id, @Param("tagids") List<Integer> tagIds);

    TBlog selectBytitle(String title);

    TBlog selectOne(Long id);

    List<Integer> selectTags(Long id);

    void deleteBlogTags(Long id);

    List<FirstPageBlog> getFirstPageBlog();

    List<TBlog> selectAllAndRecommend();

    List<TBlog> selectOneLimit(String query);

    List<TTag> selectTagOne(Long id);

    void updateViews(Long id);

    List<TBlog> getBlogsByTypes(Long id);

    List<TBlog> getBlogsByTags(Long id);

    List<String> findGroudYear();

    List<TBlog> selectByYear(String year);

    Long countBlog();
}