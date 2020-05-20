package com.ishy.blog.service.blogSerivce;

import com.ishy.blog.mapper.TBlogMapper;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.query.FirstPageBlog;
import com.ishy.blog.query.blogQuery;
import com.ishy.blog.util.MarkdownUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 红尘
 * @Date 2020/5/16 15:40
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class blogServiceImpl implements blogService {
    
    @Autowired
    TBlogMapper tBlogdao;
    
    @Override
    public TBlog getBlog(Long id) {
        return tBlogdao.selectByPrimaryKey(id);
    }
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {

        return tBlogdao.getFirstPageBlog();
    }
    @Override
    public List<TBlog> listBlog(blogQuery blog) {

        return tBlogdao.selectAll(blog);
    }

    @Override
    public int saveBlog(TBlog blog) {

        if (blog.getCreatetime()==null){
            blog.setCreatetime(new Date());
        }

        blog.setUpdatetime(new Date());
        blog.setViews(0);

        return tBlogdao.insert(blog);
    }

    @Override
    public int update(Long id,TBlog blog) throws NotFoundException {
        blog.setUpdatetime(new Date());
        return tBlogdao.updateByPrimaryKey(blog);
    }

    @Override
    public void delete(Long id) {
        this.deleteBlogTags(id);
        tBlogdao.deleteByPrimaryKey(id);
    }

    @Override
    public List<TBlog> selectAll() {
        return tBlogdao.selectAlll();
    }

    @Override
    public void saveBolgTag(TBlog blog) {

        tBlogdao.insertBolgTag(blog.getId(),blog.getTagIds());
    }

    @Override
    public TBlog getBlogs(Long id) {
        TBlog blog =tBlogdao.selectOne(id);
        List<Integer>tags =  tBlogdao.selectTags(id);
        blog.setTags(tBlogdao.selectTagOne(id));
        blog.setTagIds(tags);
        return blog;
    }

    @Override
    public TBlog getBlogsAndContent(Long id) {
        TBlog blog =tBlogdao.selectOne(id);
        List<Integer>tags =  tBlogdao.selectTags(id);
        blog.setTags(tBlogdao.selectTagOne(id));
        blog.setTagIds(tags);

        tBlogdao.updateViews(id);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    @Override
    public List<TBlog> getBlogsByTypes(Long id) {
        return tBlogdao.getBlogsByTypes(id);
    }

    @Override
    public List<TBlog> getBlogsByTags(Long id) {

        return  tBlogdao.getBlogsByTags(id);
    }

    @Override
    public Map<String, List<TBlog>> archiveBlog() {
        List<String> years = tBlogdao.findGroudYear();
        Map<String, List<TBlog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year,tBlogdao.selectByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return tBlogdao.countBlog();
    }

    @Override
    public TBlog getBlogBytitle(String title) {
        return tBlogdao.selectBytitle(title);
    }

    @Override
    public void deleteBlogTags(Long id) {
        tBlogdao.deleteBlogTags(id);
    }

    @Override
    public List<TBlog> selectAllandRecommend() {
        return tBlogdao.selectAllAndRecommend();
    }

    @Override
    public List<TBlog> selectOneLimit(String query) {
        return tBlogdao.selectOneLimit(query);
    }


}
