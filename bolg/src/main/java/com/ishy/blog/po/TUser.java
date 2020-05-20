package com.ishy.blog.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * t_user
 * @author 
 */
@Data
public class TUser implements Serializable {
    private Integer id;

    private String nickname;

    private String username;

    private String password;

    private String email;

    private String avatar;

    private Integer type;

    private Date createtime;

    private Date updatetime;

    private List<TBlog> bolgs;

}