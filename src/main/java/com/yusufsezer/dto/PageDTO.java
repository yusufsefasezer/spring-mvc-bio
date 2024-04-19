package com.yusufsezer.dto;

import com.yusufsezer.entity.Page;
import com.yusufsezer.enumeration.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PageDTO(
        @NotBlank
        @Size(min = 3, max = 50)
        String title,
        @Size(min = 10, max = 255)
        String excerpt,
        String description,
        Status status) {

    public Page toPage() {
        Page toPage = new Page();
        toPage.setTitle(title());
        toPage.setExcerpt(excerpt());
        toPage.setDescription(description());
        toPage.setStatus(status());
        return toPage;
    }

    public static Page toPerson(PageDTO pageDTO, Page page) {
        page.setTitle(pageDTO.title());
        page.setExcerpt(pageDTO.excerpt());
        page.setDescription(pageDTO.description());
        page.setStatus(pageDTO.status());
        return page;
    }

}
