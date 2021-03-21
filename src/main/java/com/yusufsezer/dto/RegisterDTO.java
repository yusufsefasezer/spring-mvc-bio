package com.yusufsezer.dto;

import com.yusufsezer.entity.Author;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO extends LoginDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past
    private LocalDate birthDate;

    private String description;

    public Author toAuthor() {
        Author toAuthor = new Author();
        toAuthor.setEmail(getEmail());
        toAuthor.setFirstName(getFirstName());
        toAuthor.setLastName(getLastName());
        toAuthor.setBirthDate(getBirthDate());
        toAuthor.setDescription(getDescription());
        return toAuthor;
    }

}
