package com.ishy.blog.service.typeService;

import com.ishy.blog.mapper.TTypeMapper;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TType;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 红尘
 * @Date 2020/5/15 19:39
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TTypeMapper typedao;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int saveType(TType type) {
        return typedao.insert(type);
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public TType getType(Long id) {
        return typedao.selectByPrimaryKey(id);
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<TType> listType() {
        return typedao.selectAll();
    }
    @Transactional(rollbackFor = {NotFoundException.class})
    @Override
    public void updateType(Long id, TType type) throws NotFoundException {
        TType t = typedao.selectByPrimaryKey(id);
        if(t == null){
            throw new NotFoundException("不存在该类型");
        }
        typedao.updateByPrimaryKey(type);

    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteType(Long id) {
        typedao.deleteByPrimaryKey(id);
    }

    @Override
    public TType findTypeByName(String name) {
        return typedao.selectByName(name);
    }

    @Override
    public List<TType> getTypeAndBolg() {
        return typedao.selectTypeAndBlog();
    }

    @Override
    public List<TBlog> listBolgType(Long id) {
        return typedao.selectBolgType(id);
    }
}
