package com.yusufsezer.controller.admin;

import com.yusufsezer.entity.Setting;
import com.yusufsezer.service.GlobalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class SettingController {

    @Autowired
    GlobalService globalService;

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @GetMapping("settings")
    public String settings(ModelMap modelMap) {

        modelMap.addAttribute("settings", true);

        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.settings");
        modelMap.addAttribute("pageTitle", pageTitle);

        Settings settings = new Settings(globalService.settingService.getSettings());
        modelMap.addAttribute("settingList", settings);
        return "admin/settings";
    }

    @PostMapping("settings")
    public String settings(
            Settings settingList,
            RedirectAttributes redirectAttributes) {

        settingList.getSettings()
                .forEach(setting -> {
                    String sKey = setting.getSKey();
                    String sValue = setting.getSValue();
                    globalService.settingService.updateSetting(sKey, sValue);
                });

        redirectAttributes.addFlashAttribute("message", "update");
        return "redirect:/admin/settings";
    }

    public class Settings {

        private List<Setting> settings;

        public Settings(List<Setting> settings) {
            this.settings = settings;
        }

        public List<Setting> getSettings() {
            return settings;
        }

        public void setSettings(List<Setting> settings) {
            this.settings = settings;
        }

    }

}
