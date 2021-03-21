package com.yusufsezer.controller;

import com.yusufsezer.dto.LoginDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @GetMapping(value = "/login")
    public String login(
            HttpServletRequest request,
            ModelMap modelMap) {

        String message = messageSourceAccessor
                .getMessage("site.page.login.error");

        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            message = (ex != null) ? ex.getMessage() : message;
        }

        modelMap.addAttribute("login", new LoginDTO());
        modelMap.addAttribute("message", message);
        return "login";
    }

}
