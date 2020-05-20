package com.ishy.blog.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 红尘
 * @Date 2020/5/17 23:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstPageBlog {
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;

    //Type
    private String typeName;

    //User
    private String nickname;
    private String avatar;

}
