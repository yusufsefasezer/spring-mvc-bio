package com.yusufsezer.dto;

import com.yusufsezer.entity.Author;
import com.yusufsezer.entity.Comment;
import com.yusufsezer.entity.Person;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    @NotBlank
    private String content;

    public Comment toComment() {
        Comment newComment = new Comment();
        newComment.setContent(getContent());
        return newComment;
    }

    public Comment toComment(Author author, Person person) {
        Comment newComment = new Comment();
        newComment.setContent(getContent());
        newComment.setAuthor(author);
        newComment.setPerson(person);
        return newComment;
    }

}
