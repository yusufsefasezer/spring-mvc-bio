package com.yusufsezer.projection;

public interface IPersonInfo {

    String getFirstName();

    String getLastName();

    default String fullName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

}
