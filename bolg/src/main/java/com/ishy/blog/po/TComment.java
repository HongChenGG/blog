package com.ishy.blog.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_comment
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TComment implements Serializable {
    private Integer id;

    private String nickname;

    private String email;

    private String content;

    private String avatar;

    private Date creatime;

    private TBlog blog;

    private List<TComment> childComments = new ArrayList<>();

    private Integer parentId;

    private String parentname;

    private Integer topid;

    private static final long serialVersionUID = 1L;

    private Boolean adminComment;
}