package com.smbms.exception;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

//@Component
public class QJExceptionHandler implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("500");
        if (e instanceof RuntimeException){
            mv.addObject("erro","服务器繁忙，请稍后重试！！！！！");
        }else if (e instanceof SQLException){
            mv.addObject("erro","该功能正在维护，请稍后重试");
        }else {
            mv.addObject("erro","服务器异常，请联系客服");
        }
        return mv;
    }
}
