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
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @JsonBackReference(value = "book_comment")
    private Book book;



    public Comment(CommentDto commentDto) {
        if(commentDto.getCommentText() != null){
            this.commentText = commentDto.getCommentText();
        }
    }

}

