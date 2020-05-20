package com.ishy.blog.web.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TTag;
import com.ishy.blog.po.TUser;
import com.ishy.blog.query.blogQuery;
import com.ishy.blog.service.blogSerivce.blogService;
import com.ishy.blog.service.blogSerivce.blogServiceImpl;
import com.ishy.blog.service.tagSerivce.TagService;
import com.ishy.blog.service.typeService.TypeService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 红尘
 * @Date 2020/5/15 16:20
 */
@Controller
@RequestMapping("admin")
public class blogController {
    private static final String INPUT="/admin/blogs-input";
    private static final String UPDATE="/admin/blogs-update";
    private static final String LIST="/admin/blogs";
    private static final String REDIRECT_LIST="redirect:/admin/blogs";
    @Autowired
    private blogService bolgServiceImpl;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @GetMapping("blogs")
    public String list(blogQuery query,@PageableDefault(size = 10) Pageable pageable, Model model){
        model.addAttribute("types",typeService.listType());
        Page<TBlog> page;

        List<TBlog> listBlog;
         page = PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize(),"b.id desc");
         listBlog = bolgServiceImpl.selectAll();
         model.addAttribute("page",page);
        if(page.getPageNum()==0){
            page.setPageNum(1);
        }
        return LIST;
    }
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blogs",new TBlog());
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTTag());
        return INPUT;
    }
    @GetMapping("/blogs/{id}/update")
    public String editinput(@PathVariable Long id, Model model){
       TBlog tblogs =  bolgServiceImpl.getBlogs(id);
       StringBuffer stringbuffer = new StringBuffer();
        for(int i = 1;i<=tblogs.getTagIds().size();i++){
            if(i!=tblogs.getTagIds().size()){
                stringbuffer.append(tblogs.getTagIds().get(i-1).toString()+",");
            }
            else{
                stringbuffer.append(tblogs.getTagIds().get(i-1).toString());
            }

        }

        model.addAttribute("blogs",tblogs);
        model.addAttribute("tagss",stringbuffer.toString());
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTTag());
        return UPDATE;
    }
        @PostMapping("/blogs")
        public String post(TBlog blog, RedirectAttributes attributes, HttpSession session){
            blog.setUser((TUser)session.getAttribute("user"));
            blog.setType(typeService.getType(blog.getType().getId()));
            int i = bolgServiceImpl.saveBlog(blog);
            TBlog newblog = bolgServiceImpl.getBlogBytitle(blog.getTitle());
            newblog.setTagIds(blog.getTagIds());
            bolgServiceImpl.saveBolgTag(newblog);
            if(i==0){
                attributes.addFlashAttribute("message","操作失败");
            }else{
                attributes.addFlashAttribute("message","操作成功");
            }
            return REDIRECT_LIST;
    }

    @PostMapping("/blogs/update")
    public String postupdate(TBlog blog, RedirectAttributes attributes, HttpSession session) throws NotFoundException {
        blog.setUser((TUser)session.getAttribute("user"));
        TBlog oldBlog =  bolgServiceImpl.getBlog(blog.getId());
        bolgServiceImpl.deleteBlogTags(blog.getId()); //删除博客跟标签的表关联
        blog.setCreatetime(oldBlog.getCreatetime());
        int i = bolgServiceImpl.update(blog.getId(),blog);//写入新博客
        bolgServiceImpl.saveBolgTag(blog); //新增表关联
        if(i==0){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){

        bolgServiceImpl.delete(id);
        bolgServiceImpl.deleteBlogTags(id);
        attributes.addFlashAttribute("message","操作成功");

        return REDIRECT_LIST;
    }
}
