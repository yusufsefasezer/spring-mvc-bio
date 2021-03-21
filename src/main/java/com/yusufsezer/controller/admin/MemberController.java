package com.yusufsezer.controller.admin;

import com.yusufsezer.dto.MemberDTO;
import com.yusufsezer.entity.Author;
import com.yusufsezer.exception.AuthorNotFound;
import com.yusufsezer.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class MemberController {

    @Autowired
    GlobalService globalService;

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @GetMapping("members")
    public String members(ModelMap modelMap) {

        modelMap.addAttribute("members", true);

        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.members");
        modelMap.addAttribute("pageTitle", pageTitle);

        modelMap.addAttribute("memberList",
                globalService.authorService.getMembers());
        return "admin/members";
    }

    @GetMapping("member-approve/{id:\\d+}")
    public String approve(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        Author foundAuthor = findAuthor(id);

        globalService.authorService.approve(foundAuthor);
        redirectAttributes.addFlashAttribute("message", "approve");
        return "redirect:/admin/members";
    }

    @GetMapping("member-edit/{id:\\d+}")
    public String edit(
            @PathVariable Long id,
            ModelMap modelMap) {

        MemberDTO memberDTO = globalService.authorService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Unable to find resource"));
        modelMap.addAttribute("members", true);

        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.members.edit",
                new Object[]{memberDTO.fullName()});
        modelMap.addAttribute("pageTitle", pageTitle);

        modelMap.addAttribute("memberDTO", memberDTO);
        return "admin/member-form";
    }

    @PostMapping("member-edit/{id:\\d+}")
    public String edit(
            @PathVariable Long id,
            MemberDTO memberDTO,
            BindingResult bindingResult,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            String pageTitle = messageSourceAccessor.getMessage("site.admin.page.members.edit",
                    new Object[]{memberDTO.fullName()});
            modelMap.addAttribute("pageTitle", pageTitle);
            return "admin/member-form";
        }

        Author foundAuthor = findAuthor(id);

        Author updateAuthor = MemberDTO.toAuthor(memberDTO, foundAuthor);
        globalService.authorService.save(updateAuthor);
        redirectAttributes.addFlashAttribute("message", "update");
        return "redirect:/admin/members";
    }

    @GetMapping("member-delete/{id:\\d+}")
    public String delete(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        globalService.authorService.delete(id);

        redirectAttributes.addFlashAttribute("message", "delete");
        return "redirect:/admin/members";
    }

    private Author findAuthor(Long id) {
        return globalService.authorService
                .getMember(id)
                .orElseThrow(AuthorNotFound::new);
    }

}
