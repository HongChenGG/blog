package com.ishy.blog.service.tagSerivce;

import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TTag;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 * @author 红尘
 * @Date 2020/5/15 17:26
 */
public interface TagService {
    int saveTTag(TTag tag);
    TTag getTTag(Long id);
    List<TTag> listTTag();
    List<TTag> listTTag(String ids);
    void updateTTag(Long id, TTag tag) throws NotFoundException;
    void deleteTTag(Long id);
    TTag findTTagByName(String name);

    List<TTag>  getTagAndBolg();

    List<TBlog> listBolgTag(Long id);
}
