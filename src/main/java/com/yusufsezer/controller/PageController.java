package com.yusufsezer.controller;

import com.yusufsezer.entity.Page;
import com.yusufsezer.exception.PageNotFound;
import com.yusufsezer.service.GlobalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    private final GlobalService globalService;

    public PageController(GlobalService globalService) {
        this.globalService = globalService;
    }

    @GetMapping("/page/{slug}")
    public String page(@PathVariable("slug") String slug, ModelMap modelMap) {
        Page foundPage = findPage(slug);
        modelMap.addAttribute("page", foundPage);
        return "page";
    }

    private Page findPage(String slug) {
        return globalService.pageService
                .findBySlug(slug)
                .orElseThrow(PageNotFound::new);
    }

}
