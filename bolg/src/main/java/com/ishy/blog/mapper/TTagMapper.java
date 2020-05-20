package com.ishy.blog.mapper;

import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TTag;

import java.util.List;

public interface TTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTag record);

    int insertSelective(TTag record);

    TTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTag record);

    int updateByPrimaryKey(TTag record);

    List<TTag> selectAll();

    TTag selectByName(String name);

    List<TTag> getTagAndBolg();

    List<TBlog> selectBolgTags(Long id);
}