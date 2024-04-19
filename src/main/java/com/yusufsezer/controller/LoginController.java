package com.yusufsezer.controller;

import com.yusufsezer.dto.LoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final MessageSourceAccessor messageSourceAccessor;

    public LoginController(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request, ModelMap modelMap) {

        String message = messageSourceAccessor.getMessage("site.page.login.error");

        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            message = (ex != null) ? ex.getMessage() : message;
        }

        modelMap.addAttribute("login", LoginDTO.empty());
        modelMap.addAttribute("message", message);
        return "login";
    }

}
