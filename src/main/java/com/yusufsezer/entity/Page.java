package com.yusufsezer.entity;

import com.yusufsezer.contract.BaseEntity;
import com.yusufsezer.enumeration.Status;
import com.yusufsezer.util.Slug;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Page extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(length = 50)
    private String title;

    @Column(length = 50)
    private String slug;

    private String excerpt;

    @Lob
    private String description;

    private Status status;

    @PrePersist
    @PreUpdate
    public void setSlugify() {
        slug = Slug.slugify(title);
    }

}
