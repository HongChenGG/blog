package com.ishy.blog.po;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * t_tag
 * @author 
 */
@Data
public class TTag implements Serializable {
    private Long id;

    private String name;

    List<TBlog> blogs;
    private static final long serialVersionUID = 1L;
}