package com.contentCreation.lssub.repository;

import com.contentCreation.lssub.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByUserNameAndContentId(String userName, String contentId);

    List<Comment> findByContentId(String contentId);

    List<Comment> findByUserName(String userName);

}
