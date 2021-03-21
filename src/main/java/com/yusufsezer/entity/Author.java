package com.yusufsezer.entity;

import com.yusufsezer.contract.BasePerson;
import com.yusufsezer.enumeration.Role;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"comments"})
public class Author extends BasePerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    private LocalDate birthDate;

    private Role role;

    private boolean active;

    @OneToMany(
            mappedBy = "author",
            orphanRemoval = true,
            cascade = CascadeType.ALL
            //fetch = FetchType.EAGER
    )
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comment> comments;

}
