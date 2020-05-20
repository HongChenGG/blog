package com.ishy.blog.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TTag;
import com.ishy.blog.po.TType;
import com.ishy.blog.query.FirstPageBlog;
import com.ishy.blog.query.blogQuery;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    blogService blogServiceImpl;
    @Autowired
    TypeService typeServiceImpl;
    @Autowired
    TagService tagServiceImpl;
    @RequestMapping("/")
    public String index(@PageableDefault(size = 10) Pageable pageable, Model model){
        List<TBlog> listBlog;
        Page<TBlog> page = PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize(),"updateTime desc");
        listBlog = blogServiceImpl.selectAll();
        Page<TBlog> pageRecommend = PageHelper.startPage(0,8,"updateTime desc");
        List<TBlog> listBlogRecommend = blogServiceImpl.selectAllandRecommend();
        List<TType> types =  typeServiceImpl.getTypeAndBolg();
        List<TType> newtypes = new ArrayList<>();
        List<TTag> tags =  tagServiceImpl.getTagAndBolg();
        List<TTag> newtags = new ArrayList<>();
        if(page.getPageNum()==0){
            page.setPageNum(1);
        }
        for(int i = 0;i<types.size()-1;i++){
            for(int j = i+1;j<types.size()-i;j++){
            if(types.get(i).getBolgs().size()<types.get(j).getBolgs().size()){
                TType t = types.get(i);
                types.set(i,types.get(j));
                types.set(j,t);
            }
            }
        }
        //首页显示多少条分类,如果显示5条,数据库里面的分类必须大于5
        if(types.size()<5){
            for(int i =0;i<types.size();i++){
                newtypes.add(types.get(i));
            }
        }else{
            for(int i =0;i<5;i++){
                newtypes.add(types.get(i));
            }
        }

        for(int i = 0;i<tags.size()-1;i++){
            for(int j = i+1;j<tags.size()-i;j++){
                if(tags.get(i).getBlogs().size()<tags.get(j).getBlogs().size()){
                    TTag t = tags.get(i);
                    tags.set(i,tags.get(j));
                    tags.set(j,t);
                }
            }
        }
        //首页显示多少条标签,如果显示5条,数据库里面的分类必须大于5

        if(tags.size()<8){
            for(int i =0;i<tags.size();i++){
                newtags.add(tags.get(i));
            }
        }else{
            for(int i =0;i<8;i++){
                newtags.add(tags.get(i));
            }
        }

        model.addAttribute("pageRecommend",pageRecommend);
        model.addAttribute("page",page);
        model.addAttribute("types",newtypes);
        model.addAttribute("tags",newtags);
        return "index";

    }

    @RequestMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){

        model.addAttribute("blog",blogServiceImpl.getBlogsAndContent(id));
        return "blog";
    }
    @RequestMapping("/search")
    public String search(@PageableDefault(size = 5) Pageable pageable, Model model,String query){
        Page<TBlog> page = PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize(),"updateTime desc");
        List<TBlog> tBlogs = blogServiceImpl.selectOneLimit(query);
        if(page.getPageNum()==0){
            page.setPageNum(1);
        }
        model.addAttribute("page",page);
        model.addAttribute("query",query);
        return "search";
    }
}
