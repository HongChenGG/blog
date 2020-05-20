package com.ishy.blog.mapper;

import com.ishy.blog.po.TUser;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser selectByUserKey(Integer id);

    TUser findByUserNameAndPassWord(@Param("username") String username,@Param("password") String password);
}