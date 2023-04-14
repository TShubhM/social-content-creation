package com.contentCreation.lssub.controllers;

import com.contentCreation.lssub.model.Comment;
import com.contentCreation.lssub.service.CommentService;
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

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addComment(comment));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCommentById(@RequestParam String commentId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteComment(commentId));
    }

    @GetMapping("/contentId")
    public ResponseEntity<List<Comment>> getCommentsByContentID(@RequestParam String contentId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCommentsByContentId(contentId));
    }

    @GetMapping("/userName")
    public ResponseEntity<List<Comment>> getCommentByUserName(@RequestParam String userName) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByUserName(userName));
    }

    @GetMapping("/userNameAndContentId")
    public ResponseEntity<List<Comment>> getCommentsByUserNameAndContentId(@RequestParam String userName, @RequestParam String contentId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByUserNameAndContentId(userName, contentId));
    }

    @PutMapping
    public ResponseEntity<Comment> updateCommentById(@RequestBody Comment comment){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCommentById(comment));
    }


}
