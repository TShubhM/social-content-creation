package com.contentCreation.lssub.controllers;

import com.contentCreation.lssub.model.Like;
import com.contentCreation.lssub.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {

    private Logger log = LoggerFactory.getLogger(LikeController.class);

    @Autowired
    private LikeService service;

    @PostMapping
    public ResponseEntity<Like> addLike(@RequestBody Like like) throws IOException, InterruptedException {
        log.info("addLike method invoked.");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addLike(like));
    }

    @GetMapping
    public ResponseEntity<List<Like>> getLikesByContentID(@RequestParam String contentID) {
        log.info("getLikesByContentID method invoked.");
        return ResponseEntity.status(HttpStatus.OK).body(service.getLikesByContentId(contentID));
    }

    @DeleteMapping
    public ResponseEntity<Like> deleteLike(@RequestParam String userName, @RequestParam String contentId) {
        log.info("deleteLike method invoked.");
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteLike(userName, contentId));
    }
}
