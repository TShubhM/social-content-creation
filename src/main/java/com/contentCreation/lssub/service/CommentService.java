package com.contentCreation.lssub.service;

import com.contentCreation.lssub.exceptions.EmptyListException;
import com.contentCreation.lssub.model.Comment;
import com.contentCreation.lssub.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private Logger log = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentRepository repository;


    public Comment addComment(Comment comment) {
        String commentId = UUID.randomUUID().toString();
        comment.setCommentId(commentId);
        return repository.save(comment);
    }

    public String deleteComment(String commentId) {
        repository.deleteById(commentId);
        return "Comment has been deleted successfully";
    }

    //get all comments on contentid
    public List<Comment> getCommentsByContentId(String contentId) {
        List<Comment> byContentId = repository.findByContentId(contentId);
        if (byContentId.size() > 0) {
            return byContentId;
        } else {
            log.error("This Post does not have any comments.");
            throw new EmptyListException("Post does not have any comments.");
        }
    }

    //    get comment by userName
    public List<Comment> getByUserName(String userName) {
        List<Comment> byUserName = repository.findByUserName(userName);
        if (byUserName.size() > 0) {
            return byUserName;
        } else {
            log.error(userName + " haven't committed anything.");
            throw new EmptyListException("You haven't committed anything.");
        }
    }

    public List<Comment> getByUserNameAndContentId(String userName, String contentId) {
        return repository.findByUserNameAndContentId(userName, contentId);
    }

    public Comment updateCommentById(@RequestBody Comment comment) {
        Comment commentToUpdated = repository.findById(comment.getCommentId()).orElseThrow(
                () -> {
                    log.error("Comment is not present.");
                    return new RuntimeException("Comment with comment id provided " + comment.getCommentId() + " is not present.");
                }
        );
        commentToUpdated.setComments(comment.getComments());
        repository.save(commentToUpdated);
        return commentToUpdated;
    }


}
