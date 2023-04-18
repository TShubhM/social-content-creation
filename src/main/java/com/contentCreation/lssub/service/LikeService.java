package com.contentCreation.lssub.service;

import com.contentCreation.lssub.exceptions.LikeAlreadyPresentException;
import com.contentCreation.lssub.model.Like;
import com.contentCreation.lssub.repository.LikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikeService {
    private Logger log = LoggerFactory.getLogger(LikeService.class);

    @Autowired
    private LikeRepository repository;

    public Like addLike(Like like) {
        String likeId = UUID.randomUUID().toString();
        like.setLikeId(likeId);
        if (repository.findByUserNameAndContentId(like.getUserName(), like.getContentId()) != null) {
            log.error("You have already given like to the content with contentId " + like.getContentId());
            throw new LikeAlreadyPresentException("You have already given like to the content with contentId " + like.getContentId());
        } else {
            return repository.save(like);
        }
    }

    public Like deleteLike(String userName, String contentId) {
        Like foundLike = repository.findByUserNameAndContentId(userName, contentId);
        if (foundLike != null) {
            repository.deleteById(foundLike.getLikeId());
        }
        return foundLike;
    }

    public List<Like> getLikesByContentId(String contentId) {
        return repository.findByContentId(contentId);
    }


}
