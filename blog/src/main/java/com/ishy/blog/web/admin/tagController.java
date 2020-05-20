package com.ishy.blog.web.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TTag;
import com.ishy.blog.service.tagSerivce.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author 红尘
 * @Date 2020/5/16 10:34
 */
@Controller
@RequestMapping("admin")
public class tagController {
    @Autowired
    @Qualifier("tagServiceImpl")
    private TagService tagServiceImpl;
    @GetMapping("tags")
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model){

        Page<TTag> page = PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize(),"id desc");
        List<TTag> tags =  tagServiceImpl.listTTag();

        if(page.getPageNum()==0){
            page.setPageNum(1);
        }
        model.addAttribute("page",page);
        return "admin/tags";
    }
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new TTag());
        return "admin/tags-input";
    }
    @PostMapping("/tags/input")
    public String post(TTag tag, RedirectAttributes attributes){
        System.out.println(tag.getId());
        if(tagServiceImpl.findTTagByName(tag.getName())!=null){
            attributes.addFlashAttribute("message","该标签已存在");
            return "redirect:/admin/tags/input";
        }
        else{
            int i = tagServiceImpl.saveTTag(tag);
            if(i ==0){
                attributes.addFlashAttribute("message","操作失败");
            }
            else{
                attributes.addFlashAttribute("message","操作成功");
            }
        }

        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/{id}/update")
    public String update(@PathVariable("id")Long id, Model model){
        TTag tag = tagServiceImpl.getTTag(id);
        model.addAttribute("tag",tag);
        return "admin/tags-update";
    }
    @PostMapping("/tags/update")
    public String updatePost(TTag tag,Model model,RedirectAttributes attributes){
        TTag tags = tagServiceImpl.findTTagByName(tag.getName());

        if(tags!=null && tag.getId().longValue()!=tag.getId().longValue()){
            attributes.addFlashAttribute("message","该标签已存在");
            return "redirect:/admin/tags/"+tag.getId()+"/update";
        }
        try {
            tagServiceImpl.updateTTag(tag.getId(),tag);
            model.addAttribute("tag",tag);
            attributes.addFlashAttribute("message","更新成功");
            return "redirect:/admin/tags";
        }catch (Exception e){
            attributes.addFlashAttribute("message","更新失败");
            return "redirect:/admin/tags";
        }


    }
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id")Long id,RedirectAttributes attributes) throws Exception {
        List<TBlog> tblogs = tagServiceImpl.listBolgTag(id);
        if(tblogs.size()>0){
            throw new Exception("该标签下存在博客，请先删除");
        }
        tagServiceImpl.deleteTTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
