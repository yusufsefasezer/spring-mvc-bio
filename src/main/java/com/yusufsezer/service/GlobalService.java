package com.yusufsezer.service;

import org.springframework.stereotype.Service;

@Service
public class GlobalService {

    public final AuthorService authorService;

    public final CommentService commentService;

    public final CustomUserDetailsService customUserDetailsService;

    public final PageService pageService;

    public final PersonService personService;

    public final SettingService settingService;

    public GlobalService(AuthorService authorService,
            CommentService commentService,
            CustomUserDetailsService customUserDetailsService,
            PageService pageService,
            PersonService personService,
            SettingService settingService) {
        this.authorService = authorService;
        this.commentService = commentService;
        this.customUserDetailsService = customUserDetailsService;
        this.pageService = pageService;
        this.personService = personService;
        this.settingService = settingService;
    }

}
