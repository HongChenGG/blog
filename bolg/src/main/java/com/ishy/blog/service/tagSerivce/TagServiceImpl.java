package com.ishy.blog.service.tagSerivce;

import com.ishy.blog.mapper.TTagMapper;
import com.ishy.blog.po.TBlog;
import com.ishy.blog.po.TTag;
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
@Transactional(rollbackFor = {Exception.class})
public class TagServiceImpl implements TagService {
    @Autowired
    TTagMapper tagdao;
    @Override
    public int saveTTag(TTag tag) {

            return tagdao.insert(tag);
    }

    @Override
    public TTag getTTag(Long id) {
        return tagdao.selectByPrimaryKey(id);
    }

    @Override
    public List<TTag> listTTag() {
        return tagdao.selectAll();
    }

    @Override
    public List<TTag> listTTag(String ids) {
        return null;
    }

    @Override
    public void updateTTag(Long id, TTag tag) throws NotFoundException {
        TTag t = tagdao.selectByPrimaryKey(id);
        if(t == null){
            throw new NotFoundException("不存在该类型");
        }
        tagdao.updateByPrimaryKey(tag);
    }

    @Override
    public void deleteTTag(Long id) {
        tagdao.deleteByPrimaryKey(id);
    }

    @Override
    public TTag findTTagByName(String name) {
        return tagdao.selectByName(name);
    }

    @Override
    public List<TTag> getTagAndBolg() {
        return  tagdao.getTagAndBolg();
    }

    @Override
    public List<TBlog> listBolgTag(Long id) {
        return tagdao.selectBolgTags(id);
    }
}
