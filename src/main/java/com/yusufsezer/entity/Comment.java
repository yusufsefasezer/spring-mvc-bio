package com.yusufsezer.entity;

import com.yusufsezer.contract.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
