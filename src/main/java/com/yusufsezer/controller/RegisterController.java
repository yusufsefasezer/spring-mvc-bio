package com.yusufsezer.controller;

import com.yusufsezer.dto.RegisterDTO;
import com.yusufsezer.exception.AuthorAlreadyExistException;
import com.yusufsezer.service.GlobalService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final GlobalService globalService;

    public RegisterController(com.yusufsezer.service.GlobalService globalService) {
        this.globalService = globalService;
    }

    @GetMapping("/register")
    public String register(ModelMap modelMap) {
        modelMap.addAttribute("registerDTO", RegisterDTO.empty());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid RegisterDTO registerDTO,
            BindingResult bindingResult,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            globalService.authorService.register(registerDTO);
        } catch (AuthorAlreadyExistException authorAlreadyExistException) {
            modelMap.addAttribute("message", "email.exists");
            modelMap.addAttribute("arguments", registerDTO.email());
            return "register";
        }

        redirectAttributes.addFlashAttribute("message", "register");
        return "redirect:register";
    }

}
