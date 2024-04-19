package com.yusufsezer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 6)
        String password) {

    public static LoginDTO empty() {
        return new LoginDTO("", "");
    }

}
