package com.ishy.blog.service.commentService;

import com.ishy.blog.mapper.TCommentMapper;
import com.ishy.blog.po.TComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 红尘
 * @Date 2020/5/19 10:47
 */
@Service
public class commentServiceImpl implements commentService {
    @Autowired
    TCommentMapper commentMapper;
    @Override
    public List<TComment> listCommentByBlogId(Long blogId) {
        return commentMapper.selectALL(blogId);
    }

    @Override
    public void saveComment(TComment comment) {
        comment.setCreatime(new Date());
            commentMapper.insert(comment);

    }
}
