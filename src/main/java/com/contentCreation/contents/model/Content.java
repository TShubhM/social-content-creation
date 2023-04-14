package com.contentCreation.contents.model;

import com.contentCreation.lssub.model.Comment;
import com.contentCreation.lssub.model.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contents")
public class Content {

    @Id
    public String contentId;

    @Column(nullable = false)
    public String userName;
    private String title;
    private String description;
    @Lob
    private byte[] mediaFilePath;
    @CreationTimestamp()
    private Date uploadedTime;
    @UpdateTimestamp
    private Date lastUpdated;

    @Transient
    private List<Comment> comments;
    @Transient
    private List<Like> likes;

    @Override
    public String toString() {
        return "Content Uploaded by User " + userName + " with content ID " + contentId + " has likes "
                + likes + " and comments " + comments;
    }


    public void setMediaFilePath(MultipartFile multipartFile) throws IOException {
        mediaFilePath = multipartFile.getBytes();
    }

}
