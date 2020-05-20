package com.ishy.blog.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 红尘
 * @Date 2020/5/16 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class blogQuery {
    String titlekey;
    Long typeId;
    boolean isRecommend ;
}
