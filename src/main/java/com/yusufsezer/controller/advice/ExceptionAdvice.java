package com.yusufsezer.controller.advice;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public ModelAndView handleError(
            HttpServletRequest httpServletRequest,
            Exception exception) {

        String output = String.format("%s - %s",
                httpServletRequest.getRequestURI(),
                exception);

        LoggerFactory
                .getLogger(exception.getClass())
                .error(output);

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("url", httpServletRequest.getRequestURL());
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

}
