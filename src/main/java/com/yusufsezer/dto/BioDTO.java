package com.yusufsezer.dto;

import com.yusufsezer.entity.Person;
import com.yusufsezer.enumeration.Status;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BioDTO extends BasePersonDTO {

    @NotBlank
    private String photo;

    @NotBlank
    private String profession;

    private Status status;

    @ElementCollection
    private Map<String, String> features;

    public Person toPerson() {
        Person toPerson = new Person();
        toPerson.setFirstName(getFirstName());
        toPerson.setLastName(getLastName());
        toPerson.setDescription(getDescription());
        toPerson.setPhoto(getPhoto());
        toPerson.setProfession(getProfession());
        toPerson.setStatus(getStatus());
        toPerson.setFeatures(getFeatures());
        return toPerson;
    }

    public static Person toPerson(BioDTO bioDTO, Person person) {
        person.setFirstName(bioDTO.getFirstName());
        person.setLastName(bioDTO.getLastName());
        person.setDescription(bioDTO.getDescription());
        person.setPhoto(bioDTO.getPhoto());
        person.setProfession(bioDTO.getProfession());
        person.setStatus(bioDTO.getStatus());
        person.setFeatures(bioDTO.getFeatures());
        return person;
    }

    public static BioDTO toBioDTO(Person person) {
        BioDTO bioDTO = new BioDTO();
        bioDTO.setFirstName(person.getFirstName());
        bioDTO.setLastName(person.getLastName());
        bioDTO.setDescription(person.getDescription());
        bioDTO.setPhoto(person.getPhoto());
        bioDTO.setProfession(person.getProfession());
        bioDTO.setStatus(person.getStatus());
        bioDTO.setFeatures(person.getFeatures());
        return bioDTO;
    }

}
