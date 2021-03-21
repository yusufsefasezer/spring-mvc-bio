package com.yusufsezer.service;

import com.yusufsezer.entity.Comment;
import com.yusufsezer.repository.CommentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yusufsezer.projection.ICommentList;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Iterable<ICommentList> getComments() {
        return commentRepository.findByOrderByIdDesc();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public long count() {
        return commentRepository.count();
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

}
