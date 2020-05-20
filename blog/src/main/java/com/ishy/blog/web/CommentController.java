package com.ishy.blog.web;

import com.ishy.blog.po.TComment;

import com.ishy.blog.po.TUser;
import com.ishy.blog.service.blogSerivce.blogService;

import com.ishy.blog.service.commentService.commentService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 红尘
 * @Date 2020/5/19 10:36
 */
@Controller
public class CommentController {
    @Autowired
    private commentService commentServiceImpl;
    @Autowired
    private blogService blogServiceImpl;


    Map<Integer,TComment> map;
    @GetMapping("comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
       List<TComment> allTComment = commentServiceImpl.listCommentByBlogId(blogId);
          map = new HashMap<Integer,TComment>();
        Map<Integer,TComment> parentmap = new HashMap<Integer,TComment>();
        List<TComment> parents = new ArrayList<TComment>();
        List<TComment> Tommentcli = new ArrayList<TComment>();
        for (TComment comment:
        allTComment) {
            map.put(comment.getId(),comment);
        }
        for (TComment comment:
                allTComment) {
            if(comment.getParentId()==-1){
                parentmap.put(comment.getId(),comment);
            }
            else{
                //子类设置 父类的名字
               comment.setParentname(map.get(comment.getParentId()).getNickname());
                Tommentcli.add(comment);
            }
        }

        for (TComment comment:
                Tommentcli ) {
            /*
            * 拿到这个子类所属的顶级id,
            * 把map的对象拿出来
            * 添加新的参数
            * 重新设置到map里面去
            * */
            Integer topid = this.getParentId(comment);

            TComment tComment = parentmap.get(topid);
            List<TComment> list;
            if(!tComment.getChildComments().isEmpty()){
                list = tComment.getChildComments();
            }
            else{
               list  = new ArrayList<>();
            }
            list.add(comment);
            tComment.setChildComments(list);
            parentmap.replace(topid,tComment);
        }
        for (TComment comment:
            parentmap.values()) {
            parents.add(comment);
        }
        model.addAttribute("comments",parents);
        System.out.println(parents);
        return "blog :: commentList";
    }

    private Integer getParentId( TComment comment) {
        TComment Parentcomment =  map.get(comment.getParentId());
        if(Parentcomment.getParentId()!=-1){
            return getParentId(Parentcomment);
        }
        else{
            return Parentcomment.getId();
        }

    }

    @RequestMapping("/comments")
    public String post(TComment comment, HttpSession session){
        TUser user = (TUser) session.getAttribute("user");
        if(user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }
        else{
            comment.setAvatar("https://api.uomg.com/api/rand.avatar");
            comment.setAdminComment(false);

        }
        Long Blogid  = comment.getBlog().getId();
        comment.setBlog(blogServiceImpl.getBlog(Blogid));

        commentServiceImpl.saveComment(comment);
        return "redirect:/comments/"+comment.getBlog().getId();
    }

}
