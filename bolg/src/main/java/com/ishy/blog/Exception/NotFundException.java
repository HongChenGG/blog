package com.ishy.blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 红尘
 * @Date 2020/5/13 21:02
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFundException extends  RuntimeException {
    public NotFundException() {
    }

    public NotFundException(String message) {
        super(message);
    }
}
