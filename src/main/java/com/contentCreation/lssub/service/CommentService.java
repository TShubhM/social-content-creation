package com.contentCreation.lssub.service;

import com.contentCreation.lssub.exceptions.EmptyListException;
import com.contentCreation.lssub.model.Comment;
import com.contentCreation.lssub.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;


    public Comment addComment(Comment comment) {
        String commentId = UUID.randomUUID().toString();
        comment.setCommentId(commentId);
        return repository.save(comment);
    }

    public String deleteComment(String commentId) {
        repository.deleteById(commentId);
        return "Comment with " + commentId + " has been deleted successfully";
    }

    //get all comments on contentid
    public List<Comment> getCommentsByContentId(String contentId) {
        List<Comment> byContentId = repository.findByContentId(contentId);
        if (byContentId.size() > 0) {
            return byContentId;
        } else {
            throw new EmptyListException("Post does not have any comments.");
        }
    }

    //    get comment by userName
    public List<Comment> getByUserName(String userName) {
        List<Comment> byUserName = repository.findByUserName(userName);
        if (byUserName.size() > 0) {
            return byUserName;
        } else {
            throw new EmptyListException("You haven't committed anything.");
        }
    }


    public List<Comment> getByUserNameAndContentId(String userName, String contentId) {
        return repository.findByUserNameAndContentId(userName, contentId);
    }

    public Comment updateCommentById(@RequestBody Comment comment) {
        Comment commentToUpdated = repository.findById(comment.getCommentId()).orElseThrow(
                () -> new RuntimeException("Comment with comment id provided " + comment.getCommentId() + " is not present.")
        );
        commentToUpdated.setComments(comment.getComments());
        repository.save(commentToUpdated);
        return commentToUpdated;
    }


}
