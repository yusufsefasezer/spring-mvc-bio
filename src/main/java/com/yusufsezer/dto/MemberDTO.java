package com.yusufsezer.dto;

import com.yusufsezer.entity.Author;
import com.yusufsezer.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO extends BasePersonDTO {

    private String email;

    private Role role;

    public MemberDTO(
            String firstName,
            String lastName,
            String description,
            String email,
            Role role) {
        setFirstName(firstName);
        setLastName(lastName);
        setDescription(description);
        setEmail(email);
        setRole(role);
    }

    public static Author toAuthor(MemberDTO memberDTO, Author author) {
        author.setFirstName(memberDTO.getFirstName());
        author.setLastName(memberDTO.getLastName());
        author.setRole(memberDTO.getRole());
        author.setDescription(memberDTO.getDescription());
        return author;
    }

}
