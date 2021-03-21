package com.yusufsezer.service;

import com.yusufsezer.entity.Setting;
import com.yusufsezer.repository.SettingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    SettingRepository settingRepository;

    public List<Setting> getSettings() {
        return settingRepository.findAll();
    }

    public void updateSetting(String sKey, String sValue) {
        settingRepository.updateSetting(sKey, sValue);
    }

}
