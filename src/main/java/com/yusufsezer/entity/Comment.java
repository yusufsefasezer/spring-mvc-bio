package com.yusufsezer.entity;

import com.yusufsezer.contract.BaseEntity;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Comment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Lob
    private String content;

    private boolean active;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Person person;

}
