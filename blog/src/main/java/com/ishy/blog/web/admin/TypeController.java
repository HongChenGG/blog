package com.ishy.blog.web.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TType;
import com.ishy.blog.service.typeService.TypeServiceImpl;
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

import java.util.List;

/**
 * @author 红尘
 * @Date 2020/5/15 20:00
 */
@Controller
@RequestMapping("admin")
public class TypeController {
    @Autowired
    private TypeServiceImpl Typeservice;
    @GetMapping("types")
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model){

            Page<TType> page = PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize(),"id desc");
            List<TType>types =  Typeservice.listType();

            if(page.getPageNum()==0){
            page.setPageNum(1);
              }
             model.addAttribute("page",page);
            return "admin/types";
    }
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new TType());
        return "admin/types-input";
    }
    @PostMapping("/types/input")
    public String post(TType type, RedirectAttributes attributes){
        System.out.println(type.getId());
        if(Typeservice.findTypeByName(type.getName())!=null){
            attributes.addFlashAttribute("message","该分类已存在");
            return "redirect:/admin/types/input";
        }
        else{
            int i = Typeservice.saveType(type);
            if(i ==0){
                attributes.addFlashAttribute("message","操作失败");
            }
            else{
                attributes.addFlashAttribute("message","操作成功");
            }
        }

        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/update")
    public String update(@PathVariable("id")Long id,Model model){
        TType type = Typeservice.getType(id);
        model.addAttribute("type",type);
        return "admin/types-update";
    }
    @PostMapping("/types/update")
    public String updatePost(TType type,Model model,RedirectAttributes attributes){
        TType types = Typeservice.findTypeByName(type.getName());

        if(types!=null && types.getId().longValue()!=type.getId().longValue()){
            attributes.addFlashAttribute("message","该分类已存在");
            return "redirect:/admin/types/"+type.getId()+"/update";
        }
       try {
           Typeservice.updateType(type.getId(),type);
           model.addAttribute("type",type);
           attributes.addFlashAttribute("message","更新成功");
           return "redirect:/admin/types";
       }catch (Exception e){
           attributes.addFlashAttribute("message","更新失败");
           return "redirect:/admin/types";
       }


    }
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id")Long id,RedirectAttributes attributes) throws Exception {
        List<TBlog> tblogs =  Typeservice.listBolgType(id);
        if(tblogs.size()>0){
            throw new Exception("该标签下存在博客，请先删除");
        }
        Typeservice.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
