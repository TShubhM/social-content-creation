package com.contentCreation.contents.repository;

import com.contentCreation.contents.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, String> {
    List<Content> findByUserName(String userName);
    Content findByContentIdAndUserName(String contentId,String userName);
}
