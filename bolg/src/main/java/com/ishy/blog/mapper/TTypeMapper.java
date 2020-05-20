package com.ishy.blog.mapper;

import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TType;

import java.util.List;

public interface TTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TType record);

    int insertSelective(TType record);

    TType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TType record);

    int updateByPrimaryKey(TType record);

    List<TType> selectAll();

    TType selectByName(String name);

    List<TType> selectTypeAndBlog();

    List<TBlog> selectBolgType(Long id);
}