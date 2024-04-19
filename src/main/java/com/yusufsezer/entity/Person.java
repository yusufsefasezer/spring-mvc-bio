package com.yusufsezer.entity;

import com.yusufsezer.contract.BasePerson;
import com.yusufsezer.enumeration.Status;
import com.yusufsezer.util.Slug;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"features", "comments"})
public class Person extends BasePerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Lob
    private String photo;

    private String slug;

    private String profession;

    private Status status;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Map<String, String> features;

    @OneToMany(
            mappedBy = "person",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Comment> comments;

    @PrePersist
    @PreUpdate
    public void setSlugify() {
        slug = Slug.slugify(fullName());
    }

    public String link() {
        return getSlug() + "-" + getId();
    }

}
