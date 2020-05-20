package com.ishy.blog.web;

import com.ishy.blog.po.TBlog;
import com.ishy.blog.service.blogSerivce.blogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author 红尘
 * @Date 2020/5/20 16:23
 */
@Controller
public class ArchiveShowController {
    @Autowired
    blogService blogServiceImpl;
        @GetMapping("/archives")
        public String archives(Model model){
            Map<String, List<TBlog>> map = blogServiceImpl.archiveBlog();
            model.addAttribute("archiveMap",map);
            model.addAttribute("blogCount",blogServiceImpl.countBlog());
            return "/archives";
        }
}
