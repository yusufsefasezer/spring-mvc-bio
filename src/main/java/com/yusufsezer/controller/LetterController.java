package com.yusufsezer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.yusufsezer.projection.IPersonList;
import com.yusufsezer.service.GlobalService;

@Controller
public class LetterController {

    @Autowired
    GlobalService globalService;

    @GetMapping("/letter-{letter:[a-z]{1}}")
    public String letter(
            @PathVariable String letter,
            ModelMap modelMap,
            Pageable pageable) {

        String letterUpperCase = letter.toUpperCase();
        Page<IPersonList> people = globalService.personService
                .findByLetter(letterUpperCase, pageable);

        modelMap.addAttribute("letter", letterUpperCase);
        modelMap.addAttribute("people", people);
        return "letter";
    }

}
