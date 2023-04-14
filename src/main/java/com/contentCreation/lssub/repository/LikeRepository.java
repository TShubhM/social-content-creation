package com.contentCreation.lssub.repository;

import com.contentCreation.lssub.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, String> {

    List<Like> findByContentId(String contentId);
    Like findByUserNameAndContentId(String userName,String contentId);

}
