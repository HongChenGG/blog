package com.ishy.blog.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TType;
import com.ishy.blog.service.blogSerivce.blogService;
import com.ishy.blog.service.blogSerivce.blogServiceImpl;
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

/**
 * @author 红尘
 * @Date 2020/5/20 10:23
 */
@Controller
public class TypeShowController {
    @Autowired
    private TypeService tpyeServiceImpl;
    @Autowired
    private blogService blogServiceImpl;
    @RequestMapping("types/{id}")
    public String types(@PageableDefault(size = 5) Pageable pageable, Model model,@PathVariable Integer id){
        List<TType> types =  tpyeServiceImpl.getTypeAndBolg();
        Page<TBlog> page = null;
        for(int i = 0;i<types.size()-1;i++){
            for(int j = i+1;j<types.size()-i;j++){
                if(types.get(i).getBolgs().size()<types.get(j).getBolgs().size()){
                    TType t = types.get(i);
                    types.set(i,types.get(j));
                    types.set(j,t);
                }
            }
        }
        List<TBlog> blogs;

        if(id==-1){
           TType type =  types.get(0);
            page  = PageHelper.startPage(1,pageable.getPageSize());
           blogs= blogServiceImpl.getBlogsByTypes(type.getId());
            id =  type.getId().intValue();
        }
        else{

            page  = PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
            blogs =  blogServiceImpl.getBlogsByTypes(id.longValue());
        }

        model.addAttribute("types",types);
        model.addAttribute("page",page);
        model.addAttribute("activceTypeId",id);
        return "types";
    }
}
