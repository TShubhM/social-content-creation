package com.contentCreation.lssub.service;

import com.contentCreation.contents.exceptions.ContentDoesNotExistException;
import com.contentCreation.contents.repository.ContentRepository;
import com.contentCreation.lssub.model.Like;
import com.contentCreation.lssub.repository.LikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class LikeService {
    private Logger log = LoggerFactory.getLogger(LikeService.class);

    @Autowired
    private LikeRepository repository;

    @Autowired
    private ContentRepository conteRepo;

    public Like addLike(Like like) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(25))
                .build();
        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/user/validity/"+like.getUserName()))
                .build();
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        String likeId = UUID.randomUUID().toString();
        like.setLikeId(likeId);
        Like byUserNameAndContentId = repository.findByUserNameAndContentId(like.getUserName(), like.getContentId());
        if (conteRepo.existsById(like.getContentId()) && response.equals(true)){
            if (byUserNameAndContentId != null) {
                deleteLike(like.getUserName(), like.getContentId());
                log.info("Removed Like");
                return byUserNameAndContentId;
            } else {
                log.info("like added by {}", likeId);
                return repository.save(like);
            }
        } else {
            throw new ContentDoesNotExistException("Either You are not a registered User or Content you are requesting has been deleted by user.");
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
