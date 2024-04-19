package com.yusufsezer.controller;

import com.yusufsezer.dto.CommentDTO;
import com.yusufsezer.entity.Author;
import com.yusufsezer.entity.Comment;
import com.yusufsezer.entity.Person;
import com.yusufsezer.exception.BioNotFound;
import com.yusufsezer.service.CustomUserDetailsService.CustomUserDetails;
import com.yusufsezer.service.GlobalService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BioController {

    private final GlobalService globalService;

    public BioController(GlobalService globalService) {
        this.globalService = globalService;
    }

    @GetMapping("/{slug}-{id:[1-9]+[0-9]*}")
    public String bio(@PathVariable("id") Long id, ModelMap modelMap) {
        Person foundPerson = findPerson(id);
        modelMap.addAttribute("person", foundPerson);
        modelMap.addAttribute("commentDTO", CommentDTO.empty());
        return "bio";
    }

    @PostMapping("/{slug}-{id:[1-9]+[0-9]*}")
    public String addComment(
            @PathVariable("id") Long id,
            @Valid CommentDTO commentDTO,
            BindingResult bindingResult,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "bio";
        }

        Person foundPerson = findPerson(id);

        Author author = customUserDetails.getAuthor();
        Comment newComment = commentDTO.toComment(author, foundPerson);
        newComment.setActive(false);

        globalService.commentService.save(newComment);
        redirectAttributes.addFlashAttribute("message", "comment");
        return String.format("redirect:%s-%s",
                foundPerson.getSlug(),
                foundPerson.getId());
    }

    private Person findPerson(Long id) {
        return globalService.personService
                .findPerson(id)
                .orElseThrow(BioNotFound::new);
    }

}
