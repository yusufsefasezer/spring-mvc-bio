package com.yusufsezer.controller.admin;

import com.yusufsezer.dto.BioDTO;
import com.yusufsezer.entity.Person;
import com.yusufsezer.exception.BioNotFound;
import com.yusufsezer.service.GlobalService;
import com.yusufsezer.view.ExcelView;
import com.yusufsezer.view.PDFView;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("BioControllerAdmin")
@RequestMapping("/admin")
public class BioController {

    @Autowired
    GlobalService globalService;

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @GetMapping("bio")
    public String bio(
            Optional<String> term,
            ModelMap modelMap,
            @SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        modelMap.addAttribute("bio", true);
        String pageTitle;
        Page<Person> people;

        if (term.isPresent()) {
            String searchTerm = term.get();
            pageTitle = String.format("\"%s\"", searchTerm);
            people = globalService.personService.findByTerm(searchTerm, pageable);
        } else {
            pageTitle = messageSourceAccessor.getMessage("site.admin.page.bio");
            people = globalService.personService.getPeople(pageable);
        }

        modelMap.addAttribute("pageTitle", pageTitle);
        modelMap.addAttribute("people", people);
        return "admin/bio";
    }

    @GetMapping("bio-add")
    public String add(ModelMap modelMap) {

        modelMap.addAttribute("bio", true);
        modelMap.addAttribute("action", "add");

        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.bio.add");
        modelMap.addAttribute("pageTitle", pageTitle);

        modelMap.addAttribute("bioDTO", new BioDTO());
        return "admin/bio-form";
    }

    @PostMapping("bio-add")
    public String add(
            @Valid BioDTO bioDTO,
            BindingResult bindingResult,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            String pageTitle = messageSourceAccessor.getMessage("site.admin.page.bio.add");
            modelMap.addAttribute("pageTitle", pageTitle);
            return "admin/bio-form";
        }

        Person toPerson = bioDTO.toPerson();
        globalService.personService.save(toPerson);
        redirectAttributes.addFlashAttribute("message", "add");
        return "redirect:/admin/bio";
    }

    @GetMapping("bio-edit/{id:\\d+}")
    public String edit(
            @PathVariable Long id,
            ModelMap modelMap) {

        Person foundPerson = findPerson(id);
        modelMap.addAttribute("bio", true);

        BioDTO bioDTO = BioDTO.toBioDTO(foundPerson);
        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.bio.edit",
                new Object[]{bioDTO.fullName()});
        modelMap.addAttribute("pageTitle", pageTitle);

        modelMap.addAttribute("bioDTO", bioDTO);
        return "admin/bio-form";
    }

    @PostMapping("bio-edit/{id:\\d+}")
    public String edit(
            @PathVariable Long id,
            @Valid BioDTO bioDTO,
            BindingResult bindingResult,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            String pageTitle = messageSourceAccessor.getMessage("site.admin.page.bio.edit",
                    new Object[]{bioDTO.fullName()});
            modelMap.addAttribute("pageTitle", pageTitle);
            return "admin/bio-form";
        }

        Person foundPerson = findPerson(id);
        Person updatedPerson = BioDTO.toPerson(bioDTO, foundPerson);
        globalService.personService.save(updatedPerson);

        redirectAttributes.addFlashAttribute("message", "update");
        return "redirect:/admin/bio-edit/".concat(String.valueOf(id));
    }

    @GetMapping("bio-delete/{id:\\d+}")
    public String delete(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        globalService.personService.delete(id);

        redirectAttributes.addFlashAttribute("message", "delete");
        return "redirect:/admin/bio";
    }

    @RequestMapping("bio-pdf")
    public ModelAndView pdf() {
        return exportView(new PDFView(this.messageSourceAccessor));
    }

    @RequestMapping("bio-excel")
    public ModelAndView excel() {
        return exportView(new ExcelView(this.messageSourceAccessor));
    }

    private ModelAndView exportView(View view) {
        ModelAndView mav = new ModelAndView();
        mav.setView(view);
        mav.addObject("people", globalService.personService.getPeopleForView());
        return mav;
    }

    private Person findPerson(Long id) {
        return globalService.personService
                .findPerson(id)
                .orElseThrow(BioNotFound::new);
    }

}
