package com.yusufsezer.controller;

import com.yusufsezer.dto.SearchDTO;
import com.yusufsezer.entity.Person;
import com.yusufsezer.service.GlobalService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    @Autowired
    GlobalService globalService;

    @GetMapping("/search")
    public String search(
            @Valid SearchDTO searchDTO,
            BindingResult bindingResult,
            ModelMap modelMap,
            Pageable pageable) {

        modelMap.addAttribute("searchTerm", searchDTO.getTerm());

        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError("term") != null
                    ? bindingResult.getFieldError("term").getDefaultMessage()
                    : "";
            modelMap.addAttribute("message", message);
            return "search";
        }

        Page<Person> people = globalService.personService
                .findByTerm(searchDTO.getTerm(), pageable);

        modelMap.addAttribute("people", people);
        return "search";
    }

}
