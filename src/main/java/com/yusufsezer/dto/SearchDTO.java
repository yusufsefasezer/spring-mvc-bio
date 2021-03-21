package com.yusufsezer.dto;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {

    @Size(min = 3, max = 20)
    private String term;

}
