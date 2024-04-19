package com.yusufsezer.controller.advice;

import com.yusufsezer.service.GlobalService;
import java.util.Arrays;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SettingAdvice {

    private final GlobalService globalService;

    public SettingAdvice(GlobalService globalService) {
        this.globalService = globalService;
    }

//    @Autowired
//    ApplicationContext applicationContext;
//
//    @ModelAttribute("themeList2")
//    public Iterable<String> themeList2() throws IOException {
//        Resource[] themes = applicationContext
//                .getResources("classpath:/theme/*.properties");
//
//        return Stream.of(themes)
//                .map(
//                        theme -> String.valueOf(theme.getFilename())
//                                .replaceFirst("[.][^.]+$", "")
//                )
//                .collect(Collectors.toList());
//    }
    @ModelAttribute("themeList")
    public Iterable<String> themeList() {
        return Arrays.asList("default", "cerulean", "cosmo", "cyborg", "darkly",
                "flatly", "journal", "litera", "lumen", "lux", "materia", "minty",
                "pulse", "sandstone", "simplex", "sketchy", "slate", "solar",
                "spacelab", "superhero", "united", "yeti");
    }

    @ModelAttribute
    public void getSetting(ModelMap modelMap) {
        globalService.settingService
                .getSettings()
                .forEach(s -> {
                    modelMap.addAttribute(s.getSKey(), s.getSValue());
                });
    }

}
