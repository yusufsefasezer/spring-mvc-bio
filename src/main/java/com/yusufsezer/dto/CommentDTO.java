package com.yusufsezer.dto;

import com.yusufsezer.entity.Author;
import com.yusufsezer.entity.Comment;
import com.yusufsezer.entity.Person;
import jakarta.validation.constraints.NotBlank;

public record CommentDTO(
        @NotBlank
        String content) {

    public static CommentDTO empty() {
        return new CommentDTO("");
    }

    public Comment toComment() {
        Comment newComment = new Comment();
        newComment.setContent(content());
        return newComment;
    }

    public Comment toComment(Author author, Person person) {
        Comment newComment = new Comment();
        newComment.setContent(content());
        newComment.setAuthor(author);
        newComment.setPerson(person);
        return newComment;
    }

}
