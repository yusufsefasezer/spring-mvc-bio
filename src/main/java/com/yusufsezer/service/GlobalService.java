package com.yusufsezer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalService {

    @Autowired
    public AuthorService authorService;

    @Autowired
    public CommentService commentService;

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    public PageService pageService;

    @Autowired
    public PersonService personService;

    @Autowired
    public SettingService settingService;

}
