package com.yusufsezer.dto;

import com.yusufsezer.entity.Author;
import com.yusufsezer.enumeration.Role;
import jakarta.validation.constraints.NotBlank;

public record MemberDTO(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String description,
        String email,
        Role role) {

    public String fullName() {
        return String.format("%s %s", firstName(), lastName());
    }

    public static Author toAuthor(MemberDTO memberDTO, Author author) {
        author.setFirstName(memberDTO.firstName());
        author.setLastName(memberDTO.lastName());
        author.setRole(memberDTO.role());
        author.setDescription(memberDTO.description());
        return author;
    }

}
