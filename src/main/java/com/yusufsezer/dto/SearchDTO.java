package com.yusufsezer.dto;

import jakarta.validation.constraints.Size;

public record SearchDTO(
        @Size(min = 3, max = 20)
        String term) {

}
