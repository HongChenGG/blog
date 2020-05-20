package com.ishy.blog.web.admin;

import com.ishy.blog.po.TUser;
import com.ishy.blog.service.userService.UserServiceImpl;
import com.ishy.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author 红尘
 * @Date 2020/5/15 14:35
 */
@Controller
@RequestMapping("admin")
public class loginController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping
    public String loginPage(){
        return "admin/login";
    }
    @RequestMapping("login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes){
        TUser user = userService.checkUser(username,MD5Utils.code(password));
        if(user!=null){
            session.setAttribute("user",user);
            user.setPassword(null);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名或者密码错误");
            return "redirect:/admin";
        }
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";


    }
}
