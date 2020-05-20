package com.ishy.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 红尘
 * @Date 2020/5/13 20:18
 */
//会拦截所有Controller的控制器
@ControllerAdvice
public class ControllerExceptionHandler {
        private Logger logger = LoggerFactory.getLogger(this.getClass());
        @ExceptionHandler(Exception.class) //这个方法可以用来做异常处理
        public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
            logger.error("Request URL :{},Exception : {}",request.getRequestURL(),e);
            if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
                throw e;
            }
            ModelAndView mv = new ModelAndView();
            mv.addObject("url",request.getRequestURL());
            mv.addObject("exception",e);
            mv.setViewName("error/error");
            return mv;
        }
}
