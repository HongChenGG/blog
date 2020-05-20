package com.ishy.blog.service.userService;

import com.ishy.blog.mapper.TUserMapper;
import com.ishy.blog.po.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 红尘
 * @Date 2020/5/15 14:25
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    TUserMapper userDao;
    @Override
    public TUser checkUser(String username, String password) {
         TUser user = userDao.findByUserNameAndPassWord(username,password);
        return user;
    }
}
