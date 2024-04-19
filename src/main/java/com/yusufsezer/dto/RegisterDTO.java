package com.yusufsezer.dto;

import com.yusufsezer.entity.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record RegisterDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 6)
        String password,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Past
        LocalDate birthDate,
        String description) {

    public Author toAuthor() {
        Author toAuthor = new Author();
        toAuthor.setEmail(email());
        toAuthor.setFirstName(firstName());
        toAuthor.setLastName(lastName());
        toAuthor.setBirthDate(birthDate());
        toAuthor.setDescription(description());
        return toAuthor;
    }

    public static RegisterDTO empty() {
        return new RegisterDTO("", "", "", "", LocalDate.now(), "");
    }

}
