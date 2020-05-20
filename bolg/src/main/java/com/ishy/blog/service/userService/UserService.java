package com.ishy.blog.service.userService;

import com.ishy.blog.po.TUser;

/**
 * @author 红尘
 * @Date 2020/5/15 14:24
 */
public interface UserService {
    TUser checkUser(String username,String password);
}
