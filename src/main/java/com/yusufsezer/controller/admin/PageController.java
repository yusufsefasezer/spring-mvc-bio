package com.yusufsezer.controller.admin;

import com.yusufsezer.dto.PageDTO;
import com.yusufsezer.service.GlobalService;
import com.yusufsezer.entity.Page;
import com.yusufsezer.enumeration.Status;
import com.yusufsezer.exception.PageNotFound;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("PageControllerAdmin")
@RequestMapping("/admin")
public class PageController {

    @Autowired
    GlobalService globalService;

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @GetMapping("pages")
    public String pages(ModelMap modelMap) {

        modelMap.addAttribute("pages", true);

        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.pages");
        modelMap.addAttribute("pageTitle", pageTitle);

        modelMap.addAttribute("pageList", globalService.pageService.getPages());
        return "admin/pages";
    }

    @GetMapping("page-add")
    public String add(ModelMap modelMap) {

        modelMap.addAttribute("pages", true);
        modelMap.addAttribute("action", "add");

        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.pages.add");
        modelMap.addAttribute("pageTitle", pageTitle);

        modelMap.addAttribute("pageDTO", new PageDTO("", "", "", Status.PUBLISHED));
        return "admin/page-form";
    }

    @PostMapping("page-add")
    public String add(
            @Valid PageDTO pageDTO,
            BindingResult bindingResult,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            String pageTitle = messageSourceAccessor.getMessage("site.admin.page.pages.add");
            modelMap.addAttribute("pageTitle", pageTitle);
        }

        Page toPage = pageDTO.toPage();
        globalService.pageService.save(toPage);
        redirectAttributes.addFlashAttribute("message", "add");
        return "redirect:/admin/pages";
    }

    @GetMapping("page-edit/{id:\\d+}")
    public String edit(
            @PathVariable Long id,
            ModelMap modelMap) {

        PageDTO pageDTO = globalService.pageService
                .findPage(id)
                .orElseThrow(() -> new PageNotFound());
        modelMap.addAttribute("pages", true);

        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.pages.edit",
                new Object[]{pageDTO.getTitle()});
        modelMap.addAttribute("pageTitle", pageTitle);

        modelMap.addAttribute("pageDTO", pageDTO);
        return "admin/page-form";
    }

    @PostMapping("page-edit/{id:\\d+}")
    public String edit(
            @PathVariable Long id,
            @Valid PageDTO pageDTO,
            BindingResult bindingResult,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            String pageTitle = messageSourceAccessor.getMessage("site.admin.page.pages.edit",
                    new Object[]{pageDTO.getTitle()});
            modelMap.addAttribute("pageTitle", pageTitle);
            return "admin/page-form";
        }

        Page foundPage = findPage(id);

        Page updatePage = PageDTO.toPerson(pageDTO, foundPage);
        globalService.pageService.save(updatePage);
        redirectAttributes.addFlashAttribute("message", "update");
        return "redirect:/admin/page-edit/".concat(String.valueOf(id));
    }

    @GetMapping("page-delete/{id:\\d+}")
    public String delete(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        globalService.pageService.deletePage(id);

        redirectAttributes.addFlashAttribute("message", "delete");
        return "redirect:/admin/pages";
    }

    private Page findPage(Long id) {
        return globalService.pageService
                .findById(id)
                .orElseThrow(PageNotFound::new);
    }

}
