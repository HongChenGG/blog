package com.ishy.blog.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TTag;
import com.ishy.blog.po.TType;
import com.ishy.blog.service.blogSerivce.blogService;
import com.ishy.blog.service.tagSerivce.TagService;
import com.ishy.blog.service.typeService.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 红尘
 * @Date 2020/5/20 10:23
 */
@Controller
public class TagShowController {
    @Autowired
    private TagService tagServiceImpl;
    @Autowired
    private blogService blogServiceImpl;
    @RequestMapping("tags/{id}")
    public String tags(@PageableDefault(size = 5) Pageable pageable, Model model,@PathVariable Integer id){

        List<TTag> tags =  tagServiceImpl.getTagAndBolg();
        Page<TBlog> page = null;
        for(int i = 0;i<tags.size()-1;i++){
            for(int j = i+1;j<tags.size()-i;j++){
                if(tags.get(i).getBlogs().size()<tags.get(j).getBlogs().size()){
                    TTag t = tags.get(i);
                    tags.set(i,tags.get(j));
                    tags.set(j,t);
                }
            }
        }

        List<TBlog> blogs;

        if(id==-1){
           TTag tag =  tags.get(0);
            page  = PageHelper.startPage(pageable.getPageNumber(),1);
           blogs= blogServiceImpl.getBlogsByTags(tag.getId());
            id =  tag.getId().intValue();
        }
        else{

            page  = PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
            blogs =  blogServiceImpl.getBlogsByTags(id.longValue());
        }

        model.addAttribute("tags",tags);
        model.addAttribute("page",page);
        model.addAttribute("activceTypeId",id);
        return "tags";
    }
}
