package com.yusufsezer.controller.advice;

import com.yusufsezer.entity.Page;
import com.yusufsezer.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class PageAdvice {

    @Autowired
    GlobalService globalService;

    @ModelAttribute("pageList")
    public Iterable<Page> getPages() {
        return globalService.pageService
                .getPages();
    }

}
