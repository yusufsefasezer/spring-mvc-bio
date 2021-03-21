package com.yusufsezer.dto;

import com.yusufsezer.entity.Page;
import com.yusufsezer.enumeration.Status;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String title;

    @Size(min = 10, max = 255)
    private String excerpt;

    private String description;

    private Status status;

    public Page toPage() {
        Page toPage = new Page();
        toPage.setTitle(getTitle());
        toPage.setExcerpt(getExcerpt());
        toPage.setDescription(getDescription());
        toPage.setStatus(getStatus());
        return toPage;
    }

    public static Page toPerson(PageDTO pageDTO, Page page) {
        page.setTitle(pageDTO.getTitle());
        page.setExcerpt(pageDTO.getExcerpt());
        page.setDescription(pageDTO.getDescription());
        page.setStatus(pageDTO.getStatus());
        return page;
    }

}
