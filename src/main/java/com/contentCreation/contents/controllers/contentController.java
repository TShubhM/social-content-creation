package com.contentCreation.contents.controllers;

import com.contentCreation.contents.model.Content;
import com.contentCreation.contents.service.ContentManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@RestController
@RequestMapping("/contents")
public class contentController {

    private Logger log = LoggerFactory.getLogger(contentController.class);

    @Autowired
    private ContentManager service;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<Content> createContent(@RequestParam String content, @RequestParam MultipartFile file) throws IOException {
        Content content1 = mapper.readValue(content, Content.class);
        log.info("Content {} created", content1);
        String fileUrl = null;

        try {
            // 1. Define a file storage location
            String fileStorageLocation = "./src/main/resources/static";

            // 2. Create an endpoint to upload profile pictures
            if (file != null) {
                // 3. Save the profile picture file
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(fileStorageLocation + File.separator + fileName);
                Files.write(path, file.getBytes());
                System.out.println(path);
                // 4. Store the file path or URL in the database
                fileUrl = "http://localhost:8080/images/" + fileName; // replace with actual file URL
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to upload profile picture", ex);
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(service.createContent(content1, fileUrl));
    }

    @DeleteMapping("/{contentId}/{userName}")
    public ResponseEntity<String> deleteContentsById(@PathVariable String contentId, @PathVariable String userName) {
        log.info("{} has been deleted by {}", contentId, userName);
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteContent(contentId, userName));
    }

    //    Try it on the browser
//    @GetMapping("id/{contentId}")
//    public ResponseEntity<byte[]> getContent(@PathVariable String contentId) {
//        byte[] bytes = service.showContents(contentId);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        log.info("content with id {} retrieved.", contentId);
//        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Content>> getAllContents() {
        log.info("Content List {}", service.getContents());
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getContents());
    }

    @GetMapping("username/{userName}")
    public ResponseEntity<List<Content>> getContentsByUserName(@PathVariable String userName) {
        log.info("Contents uploaded by {} are {}", userName, service.getContentsByUserName(userName));
        return ResponseEntity.status(HttpStatus.OK).body(service.getContentsByUserName(userName));
    }

    @GetMapping("/data/{contentId}")
    public ResponseEntity<String> getDataByContentId(@PathVariable String contentId) {
        log.info("{} has {}.", contentId, service.contentSummary(contentId));
        return ResponseEntity.status(HttpStatus.OK).body(service.contentSummary(contentId));
    }

    @PutMapping
    public ResponseEntity<Content> updateContents(@RequestBody Content content) {
        log.info("content Before update {}", service.getContentsById(content.getContentId()));
        Content content1 = service.updateContent(content);
        log.info("Contents After update {}", content1);
        return ResponseEntity.status(HttpStatus.OK).body(content1);
    }

}
