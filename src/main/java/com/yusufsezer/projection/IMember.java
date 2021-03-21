package com.yusufsezer.projection;

import com.yusufsezer.enumeration.Role;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IMember extends IPersonInfo {

    Long getId();

    String getEmail();

    Role getRole();

    LocalDate getBirthDate();

    LocalDateTime getCreatedDate();

    boolean getActive();

}
