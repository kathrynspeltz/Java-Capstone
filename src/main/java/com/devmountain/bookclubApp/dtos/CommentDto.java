package com.devmountain.bookclubApp.dtos;


import com.devmountain.bookclubApp.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements Serializable {
    private Long id;
    private String commentText;
    private GroupDto groupDto;
    private UserDto userDto;

    public CommentDto(Comment comment) {
        if (comment.getId() != null) {
            this.id = comment.getId();
        }
        if (comment.getCommentText() != null) {
            this.commentText = comment.getCommentText();
        }

    }
}
