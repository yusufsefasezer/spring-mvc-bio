package com.yusufsezer.contract;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BasePerson extends BaseEntity {

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Lob
    private String description;

    // TODO: silinip, DTO çevirilecek
    public String fullName() {
        return String.format("%s %s", firstName, lastName);
    }

}
