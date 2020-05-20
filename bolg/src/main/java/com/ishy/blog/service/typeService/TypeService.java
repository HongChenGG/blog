package com.ishy.blog.service.typeService;

import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TType;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 * @author 红尘
 * @Date 2020/5/15 17:26
 */
public interface TypeService {
    int saveType(TType type);
    TType getType(Long id);
    List<TType> listType();
    void updateType(Long id,TType type) throws NotFoundException;
    void deleteType(Long id);

    TType findTypeByName(String name);

    List<TType> getTypeAndBolg();

    List<TBlog> listBolgType(Long id);
}
