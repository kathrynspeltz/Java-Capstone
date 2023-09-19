package com.devmountain.bookclubApp.entities;

import com.devmountain.bookclubApp.dtos.CommentDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "groups_id", referencedColumnName = "id")
    @JsonBackReference(value = "groups_comment")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value = "user_comment")
    private User user;


    public Comment(CommentDto commentDto) {
        if(commentDto.getCommentText() != null){
            this.commentText = commentDto.getCommentText();
        }
    }

}

