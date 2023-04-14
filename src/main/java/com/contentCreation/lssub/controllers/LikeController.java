package com.contentCreation.lssub.controllers;

import com.contentCreation.lssub.model.Like;
import com.contentCreation.lssub.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {


    @Autowired
    private LikeService service;

    @PostMapping
    public ResponseEntity<Like> addLike(@RequestBody Like like) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addLike(like));
    }

    @GetMapping
    public ResponseEntity<List<Like>> getLikesByContentID(@RequestParam String contentID) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getLikesByContentId(contentID));
    }

    @DeleteMapping
    public ResponseEntity<Like> deleteLike(@RequestParam String userName, @RequestParam String contentId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteLike(userName, contentId));
    }

}
