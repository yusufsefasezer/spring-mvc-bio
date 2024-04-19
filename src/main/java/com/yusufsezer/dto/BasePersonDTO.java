package com.yusufsezer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePersonDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String description;

    public String fullName() {
        return String.format("%s %s", firstName, lastName);
    }

}
