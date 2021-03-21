package com.yusufsezer.repository;

import com.yusufsezer.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SettingRepository extends JpaRepository<Setting, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE #{#entityName} e SET e.sValue = ?2 WHERE e.sKey = ?1 ")
    int updateSetting(String sKey, String sValue);

}
