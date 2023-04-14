package com.contentCreation.lssub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    private String commentId;
    private String userName;
    private String contentId;
    private String comments;

    @Override
    public String toString() {
        return "Comment is given on content " + contentId + " as " + comments;
    }
}
