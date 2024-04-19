package com.yusufsezer.controller;

import com.yusufsezer.service.GlobalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final GlobalService globalService;

    //private final MessageSourceAccessor messageSourceAccessor;
    public HomeController(GlobalService globalService) {
        this.globalService = globalService;
    }

    @GetMapping("/")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("feature", globalService.personService.getRandom(4));
        modelMap.addAttribute("latest", globalService.personService.getLatest());
        modelMap.addAttribute("popular", globalService.personService.getPopular());
        return "index";
    }

}
