package com.yusufsezer.controller.advice;

import com.yusufsezer.entity.Page;
import com.yusufsezer.service.GlobalService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class PageAdvice {

    private final GlobalService globalService;

    public PageAdvice(GlobalService globalService) {
        this.globalService = globalService;
    }

    @ModelAttribute("pageList")
    public Iterable<Page> getPages() {
        return globalService.pageService.getPages();
    }

}
