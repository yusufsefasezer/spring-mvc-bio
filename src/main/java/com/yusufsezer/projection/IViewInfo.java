package com.yusufsezer.projection;

import com.yusufsezer.enumeration.Status;
import java.time.LocalDateTime;

public interface IViewInfo extends IPersonInfo {

    Long getId();

    String getProfession();

    LocalDateTime getCreatedDate();

    Status getStatus();

}
