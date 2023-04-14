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
@Table(name = "likes")
public class Like {
    @Id
    private String likeId;
    private String userName;
    private String contentId;


    @Override
    public String toString() {
        return "{User with user name " + userName + " liked the content with contentId " + contentId + "}";
    }
}
