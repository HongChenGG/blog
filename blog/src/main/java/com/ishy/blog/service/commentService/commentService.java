package com.ishy.blog.service.commentService;

import com.ishy.blog.po.TComment;


import java.util.List;

/**
 * @author 红尘
 * @Date 2020/5/19 10:45
 */
public interface commentService {
    List<TComment> listCommentByBlogId(Long blogId);
    void saveComment(TComment comment);
}
