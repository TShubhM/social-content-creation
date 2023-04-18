package com.contentCreation.lssub.controllers;

import com.contentCreation.lssub.model.Comment;
import com.contentCreation.lssub.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentService service;

    private Logger log = LoggerFactory.getLogger(CommentsController.class);

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        log.info("createComment method invoked. {}", comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addComment(comment));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCommentById(@RequestParam String commentId) {
        log.info("deleteCommentById method invoked. on commentId {}", commentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteComment(commentId));
    }

    @GetMapping("/contentId")
    public ResponseEntity<List<Comment>> getCommentsByContentID(@RequestParam String contentId) {
        log.info("getCommentsByContentID method invoked on contentId {}", contentId);
        return ResponseEntity.status(HttpStatus.OK).body(service.getCommentsByContentId(contentId));
    }

    @GetMapping("/userName")
    public ResponseEntity<List<Comment>> getCommentByUserName(@RequestParam String userName) {
        log.info("getCommentByUserName method invoked with UserName {}", userName);
        return ResponseEntity.status(HttpStatus.OK).body(service.getByUserName(userName));
    }

    @GetMapping("/userNameAndContentId")
    public ResponseEntity<List<Comment>> getCommentsByUserNameAndContentId(@RequestParam String userName, @RequestParam String contentId) {
        log.info("getCommentsByUserNameAndContentId method invoked with userName {} and contentId {}", userName, contentId);
        return ResponseEntity.status(HttpStatus.OK).body(service.getByUserNameAndContentId(userName, contentId));
    }

    @PutMapping
    public ResponseEntity<Comment> updateCommentById(@RequestBody Comment comment) {
        log.info("updateCommentById method invoked. updated Comment is " + comment);
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCommentById(comment));
    }
}
