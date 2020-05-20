package com.ishy.blog.mapper;

import com.ishy.blog.po.TComment;

import java.util.List;

public interface TCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TComment record);

    int insertSelective(TComment record);

    TComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TComment record);

    int updateByPrimaryKey(TComment record);

    List<TComment> selectALL(Long blogId);
}