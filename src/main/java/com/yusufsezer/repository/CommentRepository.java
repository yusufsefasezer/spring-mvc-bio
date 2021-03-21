package com.yusufsezer.repository;

import com.yusufsezer.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yusufsezer.projection.ICommentList;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Iterable<ICommentList> findByOrderByIdDesc();

}
