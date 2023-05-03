package com.contentCreation.contents.service;

import com.contentCreation.contents.exceptions.ContentDoesNotExistException;
import com.contentCreation.contents.model.Content;
import com.contentCreation.contents.repository.ContentRepository;
import com.contentCreation.lssub.exceptions.EmptyListException;
import com.contentCreation.lssub.model.Comment;
import com.contentCreation.lssub.model.Like;
import com.contentCreation.lssub.repository.CommentRepository;
import com.contentCreation.lssub.repository.LikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ContentManager {

    private Logger log = LoggerFactory.getLogger(ContentManager.class);

    @Autowired
    private ContentRepository repository;
    @Autowired
    private LikeRepository likeRepo;
    @Autowired
    private CommentRepository commentRepo;

    public Content createContent(Content content, MultipartFile file) throws IOException {
        String contentId = UUID.randomUUID().toString();
        content.setMediaFilePath(file);
        content.setContentId(contentId);
        repository.save(content);
        return content;
    }

    public String deleteContent(String contentId, String userName) {
        if (repository.findByContentIdAndUserName(contentId, userName) != null) {
            repository.deleteById(contentId);
            return "Deleted Successfully.";
        } else {
            log.error("Content you are trying delete not available. ");
            throw new ContentDoesNotExistException("You don't have Authorization to delete this content.");
        }
    }

    public byte[] showContents(String contentID) {
        Content content = repository.findById(contentID).get();
        byte[] mediaFilePath = content.getMediaFilePath();
        return mediaFilePath;
    }

    public Content getContentsById(String contentId) {
        return repository.findById(contentId).orElseThrow(
                () -> {
                    log.error("Content with ID " + contentId + " is not present to display.");
                    return new RuntimeException("Content with ID " + contentId + " is not present to display.");
                }
        );
    }

    public List<Content> getContents() {
        if (repository.findAll().size() > 0) return repository.findAll();
        else throw new EmptyListException("You haven't uploaded any content.");
    }

    public List<Content> getContentsByUserName(String userName) {
        if (repository.findByUserName(userName).size() > 0) return repository.findByUserName(userName);
        else throw new EmptyListException(userName + " hasn't uploaded any contents.");
    }

    public Content updateContent(Content content) {
        Content contentToUpdate = repository.findById(content.getContentId()).orElseThrow(
                () -> {
                    log.error("content does not exist");
                    return new ContentDoesNotExistException("Content does not exist");
                });

        contentToUpdate.setDescription(content.getDescription());
        contentToUpdate.setTitle(content.getTitle());
        repository.save(contentToUpdate);
        return contentToUpdate;
    }

    public String contentSummary(String contentId) {
        List<Comment> byContentId = commentRepo.findByContentId(contentId);
        List<Like> byContentId1 = likeRepo.findByContentId(contentId);
        log.info("content with contentId " + contentId + " has got " + byContentId1.size() + " Likes and " + byContentId.size() + " Comments.");
        return "content with contentId " + contentId + " has got " + byContentId1.size() + " Likes and " + byContentId.size() + " Comments.";
    }


}
