package com.yusufsezer.controller.admin;

import com.yusufsezer.entity.Comment;
import com.yusufsezer.exception.CommentNotFound;
import com.yusufsezer.service.GlobalService;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class CommentController {

    private final GlobalService globalService;

    private final MessageSourceAccessor messageSourceAccessor;

    public CommentController(GlobalService globalService, MessageSourceAccessor messageSourceAccessor) {
        this.globalService = globalService;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @GetMapping("comments")
    public String comments(ModelMap modelMap) {
        modelMap.addAttribute("comments", true);
        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.comments");
        modelMap.addAttribute("pageTitle", pageTitle);
        modelMap.addAttribute("commentList", globalService.commentService.getComments());
        return "admin/comments";
    }

    @PostMapping("comments")
    public String comments(@ModelAttribute Comment comment, RedirectAttributes redirectAttributes) {
        Comment foundComment = findComment(comment.getId());

        foundComment.setContent(comment.getContent());
        globalService.commentService.save(foundComment);

        redirectAttributes.addFlashAttribute("message", "update");
        return "redirect:/admin/comments";
    }

    @GetMapping("comment-delete/{id:\\d+}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Comment foundComment = findComment(id);

        globalService.commentService.delete(foundComment);

        redirectAttributes.addFlashAttribute("message", "delete");
        return "redirect:/admin/comments";
    }

    @GetMapping("comment-approve/{id:\\d+}")
    public String approve(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Comment foundComment = findComment(id);

        foundComment.setActive(true);
        globalService.commentService.save(foundComment);

        redirectAttributes.addFlashAttribute("message", "approve");
        return "redirect:/admin/comments";
    }

    @GetMapping("comment-disapprove/{id:\\d+}")
    public String disapprove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Comment foundComment = findComment(id);

        foundComment.setActive(false);
        globalService.commentService.save(foundComment);

        redirectAttributes.addFlashAttribute("message", "disapprove");
        return "redirect:/admin/comments";
    }

    private Comment findComment(Long id) {
        return globalService.commentService
                .findById(id)
                .orElseThrow(CommentNotFound::new);
    }

}
