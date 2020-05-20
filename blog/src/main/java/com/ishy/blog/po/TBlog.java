package com.ishy.blog.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * t_bolg
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBlog implements Serializable {
    private Long id;

    private String title;

    private String content;

    private String firstpicture;

    private String flag;

    private Integer views;

    private Boolean appreciation;

    private Boolean sharestatement;

    private Boolean commentabled;

    private Boolean published;

    private Boolean recommend;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    private TType type;

    private TUser user;

    private TComment comment;

    private List<TTag> tags = new ArrayList<>();

    public List<Integer> tagIds = new ArrayList<>();

    private String description;







}