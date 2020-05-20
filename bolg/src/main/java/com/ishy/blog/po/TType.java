package com.ishy.blog.po;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * t_type
 * @author 
 */
@Data
public class TType implements Serializable {
    private Long id;

    private String name;

    private List<TBlog> bolgs;

    private static final long serialVersionUID = 1L;
}