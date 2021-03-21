package com.yusufsezer.controller;

import com.yusufsezer.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    GlobalService globalService;

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @GetMapping("/")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("feature", globalService.personService.getRandom(4));
        modelMap.addAttribute("latest", globalService.personService.getLatest());
        modelMap.addAttribute("popular", globalService.personService.getPopular());
        return "index";
    }

}
