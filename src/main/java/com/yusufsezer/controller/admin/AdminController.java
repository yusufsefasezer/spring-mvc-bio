package com.yusufsezer.controller.admin;

import com.yusufsezer.service.CustomUserDetailsService.CustomUserDetails;
import com.yusufsezer.service.GlobalService;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final GlobalService globalService;

    private final MessageSourceAccessor messageSourceAccessor;

    public AdminController(GlobalService globalService, MessageSourceAccessor messageSourceAccessor) {
        this.globalService = globalService;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @GetMapping({"", "/"})
    public String home(ModelMap modelMap, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        modelMap.addAttribute("dashboard", true);

        String welcome = messageSourceAccessor.getMessage("site.admin.page.home.welcome",
                new Object[]{customUserDetails.getUsername()});
        modelMap.addAttribute("welcome", welcome);

        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.home");
        modelMap.addAttribute("pageTitle", pageTitle);

        modelMap.addAttribute("biographyCount", globalService.personService.getCount());
        modelMap.addAttribute("pageCount", globalService.pageService.getCount());
        modelMap.addAttribute("memberCount", globalService.authorService.count());
        modelMap.addAttribute("commentCount", globalService.commentService.count());

        modelMap.addAttribute("latest", globalService.personService.getLatest());
        return "admin/index";
    }

}
