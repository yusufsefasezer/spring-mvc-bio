package com.yusufsezer.entity;

import com.yusufsezer.contract.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Setting extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(unique = true)
    private String sKey;

    private String sValue;

}
